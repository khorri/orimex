/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { JourFerierService } from 'app/entities/jour-ferier/jour-ferier.service';
import { IJourFerier, JourFerier } from 'app/shared/model/jour-ferier.model';

describe('Service Tests', () => {
    describe('JourFerier Service', () => {
        let injector: TestBed;
        let service: JourFerierService;
        let httpMock: HttpTestingController;
        let elemDefault: IJourFerier;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(JourFerierService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new JourFerier(0, 0, currentDate, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        debut: currentDate.format(DATE_FORMAT),
                        fin: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a JourFerier', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        debut: currentDate.format(DATE_FORMAT),
                        fin: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        debut: currentDate,
                        fin: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new JourFerier(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a JourFerier', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        debut: currentDate.format(DATE_FORMAT),
                        fin: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        debut: currentDate,
                        fin: currentDate
                    },
                    returnedFromService
                );
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of JourFerier', async () => {
                const returnedFromService = Object.assign(
                    {
                        annee: 1,
                        debut: currentDate.format(DATE_FORMAT),
                        fin: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        debut: currentDate,
                        fin: currentDate
                    },
                    returnedFromService
                );
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a JourFerier', async () => {
                const rxPromise = service.delete(123).subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
