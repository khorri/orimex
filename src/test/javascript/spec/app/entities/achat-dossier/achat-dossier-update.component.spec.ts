/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatDossierUpdateComponent } from 'app/entities/achat-dossier/achat-dossier-update.component';
import { AchatDossierService } from 'app/entities/achat-dossier/achat-dossier.service';
import { AchatDossier } from 'app/shared/model/achat-dossier.model';

describe('Component Tests', () => {
    describe('AchatDossier Management Update Component', () => {
        let comp: AchatDossierUpdateComponent;
        let fixture: ComponentFixture<AchatDossierUpdateComponent>;
        let service: AchatDossierService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatDossierUpdateComponent]
            })
                .overrideTemplate(AchatDossierUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatDossierUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatDossierService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatDossier(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatDossier = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatDossier();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatDossier = entity;
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
