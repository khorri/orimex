/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatStatutDossierUpdateComponent } from 'app/entities/achat-statut-dossier/achat-statut-dossier-update.component';
import { AchatStatutDossierService } from 'app/entities/achat-statut-dossier/achat-statut-dossier.service';
import { AchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';

describe('Component Tests', () => {
    describe('AchatStatutDossier Management Update Component', () => {
        let comp: AchatStatutDossierUpdateComponent;
        let fixture: ComponentFixture<AchatStatutDossierUpdateComponent>;
        let service: AchatStatutDossierService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatStatutDossierUpdateComponent]
            })
                .overrideTemplate(AchatStatutDossierUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatStatutDossierUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatStatutDossierService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatStatutDossier(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatStatutDossier = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatStatutDossier();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatStatutDossier = entity;
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
