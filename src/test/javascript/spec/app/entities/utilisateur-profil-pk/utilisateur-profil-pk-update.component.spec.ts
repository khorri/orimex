/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurProfilPKUpdateComponent } from 'app/entities/utilisateur-profil-pk/utilisateur-profil-pk-update.component';
import { UtilisateurProfilPKService } from 'app/entities/utilisateur-profil-pk/utilisateur-profil-pk.service';
import { UtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';

describe('Component Tests', () => {
    describe('UtilisateurProfilPK Management Update Component', () => {
        let comp: UtilisateurProfilPKUpdateComponent;
        let fixture: ComponentFixture<UtilisateurProfilPKUpdateComponent>;
        let service: UtilisateurProfilPKService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurProfilPKUpdateComponent]
            })
                .overrideTemplate(UtilisateurProfilPKUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtilisateurProfilPKUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurProfilPKService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurProfilPK(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurProfilPK = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurProfilPK();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurProfilPK = entity;
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
