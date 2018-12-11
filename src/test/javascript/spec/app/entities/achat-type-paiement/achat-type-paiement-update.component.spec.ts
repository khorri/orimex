/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatTypePaiementUpdateComponent } from 'app/entities/achat-type-paiement/achat-type-paiement-update.component';
import { AchatTypePaiementService } from 'app/entities/achat-type-paiement/achat-type-paiement.service';
import { AchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';

describe('Component Tests', () => {
    describe('AchatTypePaiement Management Update Component', () => {
        let comp: AchatTypePaiementUpdateComponent;
        let fixture: ComponentFixture<AchatTypePaiementUpdateComponent>;
        let service: AchatTypePaiementService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatTypePaiementUpdateComponent]
            })
                .overrideTemplate(AchatTypePaiementUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatTypePaiementUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatTypePaiementService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatTypePaiement(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatTypePaiement = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatTypePaiement();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatTypePaiement = entity;
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
