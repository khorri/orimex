/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatConteneurReceptionUpdateComponent } from 'app/entities/achat-conteneur-reception/achat-conteneur-reception-update.component';
import { AchatConteneurReceptionService } from 'app/entities/achat-conteneur-reception/achat-conteneur-reception.service';
import { AchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';

describe('Component Tests', () => {
    describe('AchatConteneurReception Management Update Component', () => {
        let comp: AchatConteneurReceptionUpdateComponent;
        let fixture: ComponentFixture<AchatConteneurReceptionUpdateComponent>;
        let service: AchatConteneurReceptionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatConteneurReceptionUpdateComponent]
            })
                .overrideTemplate(AchatConteneurReceptionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatConteneurReceptionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatConteneurReceptionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatConteneurReception(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatConteneurReception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatConteneurReception();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatConteneurReception = entity;
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
