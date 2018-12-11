/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleLigneProformaUpdateComponent } from 'app/entities/achat-article-ligne-proforma/achat-article-ligne-proforma-update.component';
import { AchatArticleLigneProformaService } from 'app/entities/achat-article-ligne-proforma/achat-article-ligne-proforma.service';
import { AchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';

describe('Component Tests', () => {
    describe('AchatArticleLigneProforma Management Update Component', () => {
        let comp: AchatArticleLigneProformaUpdateComponent;
        let fixture: ComponentFixture<AchatArticleLigneProformaUpdateComponent>;
        let service: AchatArticleLigneProformaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleLigneProformaUpdateComponent]
            })
                .overrideTemplate(AchatArticleLigneProformaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatArticleLigneProformaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArticleLigneProformaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArticleLigneProforma(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArticleLigneProforma = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArticleLigneProforma();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArticleLigneProforma = entity;
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
