/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AchatArticleConteneurReceptionService } from 'app/entities/achat-article-conteneur-reception/achat-article-conteneur-reception.service';
import { IAchatArticleConteneurReception, AchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';

describe('Service Tests', () => {
    describe('AchatArticleConteneurReception Service', () => {
        let injector: TestBed;
        let service: AchatArticleConteneurReceptionService;
        let httpMock: HttpTestingController;
        let elemDefault: IAchatArticleConteneurReception;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AchatArticleConteneurReceptionService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new AchatArticleConteneurReception(0, 0, 0, 0, 0);
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

            it('should create a AchatArticleConteneurReception', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new AchatArticleConteneurReception(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AchatArticleConteneurReception', async () => {
                const returnedFromService = Object.assign(
                    {
                        idArticleConteneurReception: 1,
                        dimension: 1,
                        nombreCaissestc: 1,
                        nombreFeuillecaisse: 1
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

            it('should return a list of AchatArticleConteneurReception', async () => {
                const returnedFromService = Object.assign(
                    {
                        idArticleConteneurReception: 1,
                        dimension: 1,
                        nombreCaissestc: 1,
                        nombreFeuillecaisse: 1
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

            it('should delete a AchatArticleConteneurReception', async () => {
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
