/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AchatArticleLigneProformaService } from 'app/entities/achat-article-ligne-proforma/achat-article-ligne-proforma.service';
import { IAchatArticleLigneProforma, AchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';

describe('Service Tests', () => {
    describe('AchatArticleLigneProforma Service', () => {
        let injector: TestBed;
        let service: AchatArticleLigneProformaService;
        let httpMock: HttpTestingController;
        let elemDefault: IAchatArticleLigneProforma;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AchatArticleLigneProformaService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new AchatArticleLigneProforma(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find(123)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a AchatArticleLigneProforma', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new AchatArticleLigneProforma(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AchatArticleLigneProforma', async () => {
                const returnedFromService = Object.assign(
                    {
                        idArticleLigneProforma: 1,
                        numeroSequence: 1,
                        nombreCaissesTc: 1,
                        nombreFeuillesCaisse: 1,
                        dimention: 1,
                        quantite: 1,
                        prixUnitaire: 1,
                        montant: 1,
                        poids: 1
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of AchatArticleLigneProforma', async () => {
                const returnedFromService = Object.assign(
                    {
                        idArticleLigneProforma: 1,
                        numeroSequence: 1,
                        nombreCaissesTc: 1,
                        nombreFeuillesCaisse: 1,
                        dimention: 1,
                        quantite: 1,
                        prixUnitaire: 1,
                        montant: 1,
                        poids: 1
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
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

            it('should delete a AchatArticleLigneProforma', async () => {
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
