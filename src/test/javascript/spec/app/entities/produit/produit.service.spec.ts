/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { ProduitService } from 'app/entities/produit/produit.service';
import { IProduit, Produit } from 'app/shared/model/produit.model';

describe('Service Tests', () => {
    describe('Produit Service', () => {
        let injector: TestBed;
        let service: ProduitService;
        let httpMock: HttpTestingController;
        let elemDefault: IProduit;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(ProduitService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Produit(0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

            it('should create a Produit', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Produit(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Produit', async () => {
                const returnedFromService = Object.assign(
                    {
                        idProduit: 1,
                        designationProduit: 'BBBBBB',
                        dimension: 'BBBBBB',
                        epaisseur: 'BBBBBB',
                        largeure: 1,
                        longueur: 1,
                        referenceProduit: 'BBBBBB',
                        poids: 1,
                        libelleCouleur: 'BBBBBB',
                        libelleFamille: 'BBBBBB',
                        libelleOrigine: 'BBBBBB'
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

            it('should return a list of Produit', async () => {
                const returnedFromService = Object.assign(
                    {
                        idProduit: 1,
                        designationProduit: 'BBBBBB',
                        dimension: 'BBBBBB',
                        epaisseur: 'BBBBBB',
                        largeure: 1,
                        longueur: 1,
                        referenceProduit: 'BBBBBB',
                        poids: 1,
                        libelleCouleur: 'BBBBBB',
                        libelleFamille: 'BBBBBB',
                        libelleOrigine: 'BBBBBB'
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

            it('should delete a Produit', async () => {
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
