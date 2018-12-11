/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { StockReceptionService } from 'app/entities/stock-reception/stock-reception.service';
import { IStockReception, StockReception } from 'app/shared/model/stock-reception.model';

describe('Service Tests', () => {
    describe('StockReception Service', () => {
        let injector: TestBed;
        let service: StockReceptionService;
        let httpMock: HttpTestingController;
        let elemDefault: IStockReception;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(StockReceptionService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new StockReception(0, 0, currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateReception: currentDate.format(DATE_FORMAT)
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

            it('should create a StockReception', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateReception: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateReception: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new StockReception(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a StockReception', async () => {
                const returnedFromService = Object.assign(
                    {
                        idOperation: 1,
                        dateReception: currentDate.format(DATE_FORMAT),
                        numeroOperation: 'BBBBBB',
                        numeroBonEntree: 'BBBBBB',
                        numeroConstatNonConformite: 'BBBBBB',
                        isValide: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateReception: currentDate
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

            it('should return a list of StockReception', async () => {
                const returnedFromService = Object.assign(
                    {
                        idOperation: 1,
                        dateReception: currentDate.format(DATE_FORMAT),
                        numeroOperation: 'BBBBBB',
                        numeroBonEntree: 'BBBBBB',
                        numeroConstatNonConformite: 'BBBBBB',
                        isValide: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateReception: currentDate
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

            it('should delete a StockReception', async () => {
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
