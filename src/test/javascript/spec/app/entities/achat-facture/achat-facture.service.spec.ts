/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AchatFactureService } from 'app/entities/achat-facture/achat-facture.service';
import { IAchatFacture, AchatFacture } from 'app/shared/model/achat-facture.model';

describe('Service Tests', () => {
    describe('AchatFacture Service', () => {
        let injector: TestBed;
        let service: AchatFactureService;
        let httpMock: HttpTestingController;
        let elemDefault: IAchatFacture;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AchatFactureService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new AchatFacture(
                0,
                0,
                'AAAAAAA',
                currentDate,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                currentDate,
                'AAAAAAA',
                currentDate,
                0,
                0,
                currentDate,
                0,
                0,
                currentDate,
                0,
                currentDate,
                currentDate,
                0,
                currentDate,
                0,
                0
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateFacture: currentDate.format(DATE_FORMAT),
                        dateBl: currentDate.format(DATE_FORMAT),
                        dateEcheance: currentDate.format(DATE_FORMAT),
                        dateValeur: currentDate.format(DATE_FORMAT),
                        echecanceFinancement: currentDate.format(DATE_FORMAT),
                        dateReglement: currentDate.format(DATE_FORMAT),
                        derniereEcheance: currentDate.format(DATE_FORMAT),
                        echeanceRefinancement: currentDate.format(DATE_FORMAT)
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

            it('should create a AchatFacture', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateFacture: currentDate.format(DATE_FORMAT),
                        dateBl: currentDate.format(DATE_FORMAT),
                        dateEcheance: currentDate.format(DATE_FORMAT),
                        dateValeur: currentDate.format(DATE_FORMAT),
                        echecanceFinancement: currentDate.format(DATE_FORMAT),
                        dateReglement: currentDate.format(DATE_FORMAT),
                        derniereEcheance: currentDate.format(DATE_FORMAT),
                        echeanceRefinancement: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateFacture: currentDate,
                        dateBl: currentDate,
                        dateEcheance: currentDate,
                        dateValeur: currentDate,
                        echecanceFinancement: currentDate,
                        dateReglement: currentDate,
                        derniereEcheance: currentDate,
                        echeanceRefinancement: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new AchatFacture(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AchatFacture', async () => {
                const returnedFromService = Object.assign(
                    {
                        idFacture: 1,
                        numeroFacture: 'BBBBBB',
                        dateFacture: currentDate.format(DATE_FORMAT),
                        montantFob: 1,
                        montantFret: 1,
                        montantTotal: 1,
                        nombreTc: 1,
                        poids: 1,
                        quantite: 1,
                        ajustement: 1,
                        numeroPiece: 'BBBBBB',
                        dateBl: currentDate.format(DATE_FORMAT),
                        numeroBl: 'BBBBBB',
                        dateEcheance: currentDate.format(DATE_FORMAT),
                        etat: 1,
                        banqueReglement: 1,
                        dateValeur: currentDate.format(DATE_FORMAT),
                        cours: 1,
                        montantDh: 1,
                        echecanceFinancement: currentDate.format(DATE_FORMAT),
                        interet1: 1,
                        dateReglement: currentDate.format(DATE_FORMAT),
                        derniereEcheance: currentDate.format(DATE_FORMAT),
                        transmise: 1,
                        echeanceRefinancement: currentDate.format(DATE_FORMAT),
                        interet2: 1,
                        interet1Regle: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateFacture: currentDate,
                        dateBl: currentDate,
                        dateEcheance: currentDate,
                        dateValeur: currentDate,
                        echecanceFinancement: currentDate,
                        dateReglement: currentDate,
                        derniereEcheance: currentDate,
                        echeanceRefinancement: currentDate
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

            it('should return a list of AchatFacture', async () => {
                const returnedFromService = Object.assign(
                    {
                        idFacture: 1,
                        numeroFacture: 'BBBBBB',
                        dateFacture: currentDate.format(DATE_FORMAT),
                        montantFob: 1,
                        montantFret: 1,
                        montantTotal: 1,
                        nombreTc: 1,
                        poids: 1,
                        quantite: 1,
                        ajustement: 1,
                        numeroPiece: 'BBBBBB',
                        dateBl: currentDate.format(DATE_FORMAT),
                        numeroBl: 'BBBBBB',
                        dateEcheance: currentDate.format(DATE_FORMAT),
                        etat: 1,
                        banqueReglement: 1,
                        dateValeur: currentDate.format(DATE_FORMAT),
                        cours: 1,
                        montantDh: 1,
                        echecanceFinancement: currentDate.format(DATE_FORMAT),
                        interet1: 1,
                        dateReglement: currentDate.format(DATE_FORMAT),
                        derniereEcheance: currentDate.format(DATE_FORMAT),
                        transmise: 1,
                        echeanceRefinancement: currentDate.format(DATE_FORMAT),
                        interet2: 1,
                        interet1Regle: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateFacture: currentDate,
                        dateBl: currentDate,
                        dateEcheance: currentDate,
                        dateValeur: currentDate,
                        echecanceFinancement: currentDate,
                        dateReglement: currentDate,
                        derniereEcheance: currentDate,
                        echeanceRefinancement: currentDate
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

            it('should delete a AchatFacture', async () => {
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
