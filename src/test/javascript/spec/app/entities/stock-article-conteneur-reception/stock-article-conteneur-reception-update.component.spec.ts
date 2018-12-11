/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { StockArticleConteneurReceptionUpdateComponent } from 'app/entities/stock-article-conteneur-reception/stock-article-conteneur-reception-update.component';
import { StockArticleConteneurReceptionService } from 'app/entities/stock-article-conteneur-reception/stock-article-conteneur-reception.service';
import { StockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';

describe('Component Tests', () => {
    describe('StockArticleConteneurReception Management Update Component', () => {
        let comp: StockArticleConteneurReceptionUpdateComponent;
        let fixture: ComponentFixture<StockArticleConteneurReceptionUpdateComponent>;
        let service: StockArticleConteneurReceptionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockArticleConteneurReceptionUpdateComponent]
            })
                .overrideTemplate(StockArticleConteneurReceptionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StockArticleConteneurReceptionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StockArticleConteneurReceptionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new StockArticleConteneurReception(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.stockArticleConteneurReception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new StockArticleConteneurReception();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.stockArticleConteneurReception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
