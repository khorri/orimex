/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleConteneurReceptionUpdateComponent } from 'app/entities/achat-article-conteneur-reception/achat-article-conteneur-reception-update.component';
import { AchatArticleConteneurReceptionService } from 'app/entities/achat-article-conteneur-reception/achat-article-conteneur-reception.service';
import { AchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';

describe('Component Tests', () => {
    describe('AchatArticleConteneurReception Management Update Component', () => {
        let comp: AchatArticleConteneurReceptionUpdateComponent;
        let fixture: ComponentFixture<AchatArticleConteneurReceptionUpdateComponent>;
        let service: AchatArticleConteneurReceptionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleConteneurReceptionUpdateComponent]
            })
                .overrideTemplate(AchatArticleConteneurReceptionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatArticleConteneurReceptionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArticleConteneurReceptionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArticleConteneurReception(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArticleConteneurReception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArticleConteneurReception();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArticleConteneurReception = entity;
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
