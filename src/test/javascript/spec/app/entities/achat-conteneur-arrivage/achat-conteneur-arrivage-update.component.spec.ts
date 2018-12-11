/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatConteneurArrivageUpdateComponent } from 'app/entities/achat-conteneur-arrivage/achat-conteneur-arrivage-update.component';
import { AchatConteneurArrivageService } from 'app/entities/achat-conteneur-arrivage/achat-conteneur-arrivage.service';
import { AchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';

describe('Component Tests', () => {
    describe('AchatConteneurArrivage Management Update Component', () => {
        let comp: AchatConteneurArrivageUpdateComponent;
        let fixture: ComponentFixture<AchatConteneurArrivageUpdateComponent>;
        let service: AchatConteneurArrivageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatConteneurArrivageUpdateComponent]
            })
                .overrideTemplate(AchatConteneurArrivageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatConteneurArrivageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatConteneurArrivageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatConteneurArrivage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatConteneurArrivage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatConteneurArrivage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatConteneurArrivage = entity;
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
