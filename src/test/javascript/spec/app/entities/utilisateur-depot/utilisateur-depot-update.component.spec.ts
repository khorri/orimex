/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurDepotUpdateComponent } from 'app/entities/utilisateur-depot/utilisateur-depot-update.component';
import { UtilisateurDepotService } from 'app/entities/utilisateur-depot/utilisateur-depot.service';
import { UtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';

describe('Component Tests', () => {
    describe('UtilisateurDepot Management Update Component', () => {
        let comp: UtilisateurDepotUpdateComponent;
        let fixture: ComponentFixture<UtilisateurDepotUpdateComponent>;
        let service: UtilisateurDepotService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurDepotUpdateComponent]
            })
                .overrideTemplate(UtilisateurDepotUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtilisateurDepotUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurDepotService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurDepot(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurDepot = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurDepot();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurDepot = entity;
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
