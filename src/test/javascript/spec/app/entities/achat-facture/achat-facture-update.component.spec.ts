/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatFactureUpdateComponent } from 'app/entities/achat-facture/achat-facture-update.component';
import { AchatFactureService } from 'app/entities/achat-facture/achat-facture.service';
import { AchatFacture } from 'app/shared/model/achat-facture.model';

describe('Component Tests', () => {
    describe('AchatFacture Management Update Component', () => {
        let comp: AchatFactureUpdateComponent;
        let fixture: ComponentFixture<AchatFactureUpdateComponent>;
        let service: AchatFactureService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatFactureUpdateComponent]
            })
                .overrideTemplate(AchatFactureUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatFactureUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatFactureService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatFacture(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatFacture = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatFacture();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatFacture = entity;
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
