/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { AchatConteneurArrivageService } from 'app/entities/achat-conteneur-arrivage/achat-conteneur-arrivage.service';
import { IAchatConteneurArrivage, AchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';

describe('Service Tests', () => {
    describe('AchatConteneurArrivage Service', () => {
        let injector: TestBed;
        let service: AchatConteneurArrivageService;
        let httpMock: HttpTestingController;
        let elemDefault: IAchatConteneurArrivage;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(AchatConteneurArrivageService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new AchatConteneurArrivage(0, 0, 0, 'AAAAAAA', 0, 0, 0);
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

            it('should create a AchatConteneurArrivage', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 0
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new AchatConteneurArrivage(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a AchatConteneurArrivage', async () => {
                const returnedFromService = Object.assign(
                    {
                        idConteneurArrivage: 1,
                        montant: 1,
                        numeroConteneurs: 'BBBBBB',
                        numeroSequence: 1,
                        poids: 1,
                        quantite: 1
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

            it('should return a list of AchatConteneurArrivage', async () => {
                const returnedFromService = Object.assign(
                    {
                        idConteneurArrivage: 1,
                        montant: 1,
                        numeroConteneurs: 'BBBBBB',
                        numeroSequence: 1,
                        poids: 1,
                        quantite: 1
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

            it('should delete a AchatConteneurArrivage', async () => {
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
