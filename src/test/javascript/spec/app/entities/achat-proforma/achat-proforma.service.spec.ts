/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AchatProformaService } from 'app/entities/achat-proforma/achat-proforma.service';
import { IAchatProforma, AchatProforma } from 'app/shared/model/achat-proforma.model';

describe('Service Tests', () => {
    describe('AchatProforma Service', () => {
        let injector: TestBed;
        let service: AchatProformaService;
        let httpMock: HttpTestingController;
        let elemDefault: IAchatProforma;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AchatProformaService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new AchatProforma(0, 0, 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, currentDate);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateProforma: currentDate.format(DATE_FORMAT)
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

            it('should create a AchatProforma', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateProforma: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateProforma: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new AchatProforma(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AchatProforma', async () => {
                const returnedFromService = Object.assign(
                    {
                        idProforma: 1,
                        nombreTc: 1,
                        coutFob: 1,
                        coutFret: 1,
                        montantTotal: 1,
                        numeroBonProforma: 'BBBBBB',
                        typeAcht: 'BBBBBB',
                        poids: 1,
                        dateProforma: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateProforma: currentDate
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

            it('should return a list of AchatProforma', async () => {
                const returnedFromService = Object.assign(
                    {
                        idProforma: 1,
                        nombreTc: 1,
                        coutFob: 1,
                        coutFret: 1,
                        montantTotal: 1,
                        numeroBonProforma: 'BBBBBB',
                        typeAcht: 'BBBBBB',
                        poids: 1,
                        dateProforma: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateProforma: currentDate
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

            it('should delete a AchatProforma', async () => {
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
