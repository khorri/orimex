/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleConteneurArrivageUpdateComponent } from 'app/entities/achat-article-conteneur-arrivage/achat-article-conteneur-arrivage-update.component';
import { AchatArticleConteneurArrivageService } from 'app/entities/achat-article-conteneur-arrivage/achat-article-conteneur-arrivage.service';
import { AchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';

describe('Component Tests', () => {
    describe('AchatArticleConteneurArrivage Management Update Component', () => {
        let comp: AchatArticleConteneurArrivageUpdateComponent;
        let fixture: ComponentFixture<AchatArticleConteneurArrivageUpdateComponent>;
        let service: AchatArticleConteneurArrivageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleConteneurArrivageUpdateComponent]
            })
                .overrideTemplate(AchatArticleConteneurArrivageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatArticleConteneurArrivageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArticleConteneurArrivageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArticleConteneurArrivage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArticleConteneurArrivage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArticleConteneurArrivage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArticleConteneurArrivage = entity;
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
