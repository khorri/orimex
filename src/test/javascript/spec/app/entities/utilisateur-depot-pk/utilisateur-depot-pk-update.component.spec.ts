/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurDepotPKUpdateComponent } from 'app/entities/utilisateur-depot-pk/utilisateur-depot-pk-update.component';
import { UtilisateurDepotPKService } from 'app/entities/utilisateur-depot-pk/utilisateur-depot-pk.service';
import { UtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';

describe('Component Tests', () => {
    describe('UtilisateurDepotPK Management Update Component', () => {
        let comp: UtilisateurDepotPKUpdateComponent;
        let fixture: ComponentFixture<UtilisateurDepotPKUpdateComponent>;
        let service: UtilisateurDepotPKService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurDepotPKUpdateComponent]
            })
                .overrideTemplate(UtilisateurDepotPKUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtilisateurDepotPKUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurDepotPKService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurDepotPK(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurDepotPK = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurDepotPK();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurDepotPK = entity;
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
