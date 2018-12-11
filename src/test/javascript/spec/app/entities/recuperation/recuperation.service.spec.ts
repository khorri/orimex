/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { RecuperationService } from 'app/entities/recuperation/recuperation.service';
import { IRecuperation, Recuperation } from 'app/shared/model/recuperation.model';

describe('Service Tests', () => {
    describe('Recuperation Service', () => {
        let injector: TestBed;
        let service: RecuperationService;
        let httpMock: HttpTestingController;
        let elemDefault: IRecuperation;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(RecuperationService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new Recuperation(0, 0, currentDate, 0, 'AAAAAAA', 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateOperation: currentDate.format(DATE_TIME_FORMAT)
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

            it('should create a Recuperation', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateOperation: currentDate.format(DATE_TIME_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateOperation: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new Recuperation(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Recuperation', async () => {
                const returnedFromService = Object.assign(
                    {
                        idOperation: 1,
                        dateOperation: currentDate.format(DATE_TIME_FORMAT),
                        nombrePanneaux: 1,
                        numeroOperation: 'BBBBBB',
                        superficie: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateOperation: currentDate
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

            it('should return a list of Recuperation', async () => {
                const returnedFromService = Object.assign(
                    {
                        idOperation: 1,
                        dateOperation: currentDate.format(DATE_TIME_FORMAT),
                        nombrePanneaux: 1,
                        numeroOperation: 'BBBBBB',
                        superficie: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateOperation: currentDate
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

            it('should delete a Recuperation', async () => {
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
