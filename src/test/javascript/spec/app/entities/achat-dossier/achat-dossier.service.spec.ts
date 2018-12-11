/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AchatDossierService } from 'app/entities/achat-dossier/achat-dossier.service';
import { IAchatDossier, AchatDossier } from 'app/shared/model/achat-dossier.model';

describe('Service Tests', () => {
    describe('AchatDossier Service', () => {
        let injector: TestBed;
        let service: AchatDossierService;
        let httpMock: HttpTestingController;
        let elemDefault: IAchatDossier;
        let currentDate: moment.Moment;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AchatDossierService);
            httpMock = injector.get(HttpTestingController);
            currentDate = moment();

            elemDefault = new AchatDossier(
                0,
                0,
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                'AAAAAAA',
                0,
                'AAAAAAA',
                'AAAAAAA',
                currentDate,
                0,
                0,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                currentDate,
                0,
                0,
                0,
                0,
                'AAAAAAA',
                0,
                0
            );
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign(
                    {
                        dateCreation: currentDate.format(DATE_FORMAT),
                        dateExpedition: currentDate.format(DATE_FORMAT),
                        dateOuverture: currentDate.format(DATE_FORMAT),
                        dateValiditeEi: currentDate.format(DATE_FORMAT),
                        dateLimiteExp: currentDate.format(DATE_FORMAT),
                        dateValiditeLc: currentDate.format(DATE_FORMAT)
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

            it('should create a AchatDossier', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0,
                        dateCreation: currentDate.format(DATE_FORMAT),
                        dateExpedition: currentDate.format(DATE_FORMAT),
                        dateOuverture: currentDate.format(DATE_FORMAT),
                        dateValiditeEi: currentDate.format(DATE_FORMAT),
                        dateLimiteExp: currentDate.format(DATE_FORMAT),
                        dateValiditeLc: currentDate.format(DATE_FORMAT)
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateCreation: currentDate,
                        dateExpedition: currentDate,
                        dateOuverture: currentDate,
                        dateValiditeEi: currentDate,
                        dateLimiteExp: currentDate,
                        dateValiditeLc: currentDate
                    },
                    returnedFromService
                );
                service
                    .create(new AchatDossier(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AchatDossier', async () => {
                const returnedFromService = Object.assign(
                    {
                        idDossier: 1,
                        numeroDossier: 'BBBBBB',
                        codeFournisseur: 'BBBBBB',
                        designationFournisseur: 'BBBBBB',
                        incoterm: 'BBBBBB',
                        reference: 'BBBBBB',
                        tolerance: 1,
                        numeroEi: 'BBBBBB',
                        numeroEiComp: 'BBBBBB',
                        dateCreation: currentDate.format(DATE_FORMAT),
                        delaiPaiement: 1,
                        delaiValiditeLc: 1,
                        dateExpedition: currentDate.format(DATE_FORMAT),
                        dateOuverture: currentDate.format(DATE_FORMAT),
                        dateValiditeEi: currentDate.format(DATE_FORMAT),
                        dateLimiteExp: currentDate.format(DATE_FORMAT),
                        dateValiditeLc: currentDate.format(DATE_FORMAT),
                        montnatTotal: 1,
                        montnatFob: 1,
                        montnatFret: 1,
                        totalTc: 1,
                        designationBanque: 'BBBBBB',
                        paiementAvue: 1,
                        encours: 1
                    },
                    elemDefault
                );

                const expected = Object.assign(
                    {
                        dateCreation: currentDate,
                        dateExpedition: currentDate,
                        dateOuverture: currentDate,
                        dateValiditeEi: currentDate,
                        dateLimiteExp: currentDate,
                        dateValiditeLc: currentDate
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

            it('should return a list of AchatDossier', async () => {
                const returnedFromService = Object.assign(
                    {
                        idDossier: 1,
                        numeroDossier: 'BBBBBB',
                        codeFournisseur: 'BBBBBB',
                        designationFournisseur: 'BBBBBB',
                        incoterm: 'BBBBBB',
                        reference: 'BBBBBB',
                        tolerance: 1,
                        numeroEi: 'BBBBBB',
                        numeroEiComp: 'BBBBBB',
                        dateCreation: currentDate.format(DATE_FORMAT),
                        delaiPaiement: 1,
                        delaiValiditeLc: 1,
                        dateExpedition: currentDate.format(DATE_FORMAT),
                        dateOuverture: currentDate.format(DATE_FORMAT),
                        dateValiditeEi: currentDate.format(DATE_FORMAT),
                        dateLimiteExp: currentDate.format(DATE_FORMAT),
                        dateValiditeLc: currentDate.format(DATE_FORMAT),
                        montnatTotal: 1,
                        montnatFob: 1,
                        montnatFret: 1,
                        totalTc: 1,
                        designationBanque: 'BBBBBB',
                        paiementAvue: 1,
                        encours: 1
                    },
                    elemDefault
                );
                const expected = Object.assign(
                    {
                        dateCreation: currentDate,
                        dateExpedition: currentDate,
                        dateOuverture: currentDate,
                        dateValiditeEi: currentDate,
                        dateLimiteExp: currentDate,
                        dateValiditeLc: currentDate
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

            it('should delete a AchatDossier', async () => {
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
