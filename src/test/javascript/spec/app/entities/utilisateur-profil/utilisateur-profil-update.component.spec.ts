/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurProfilUpdateComponent } from 'app/entities/utilisateur-profil/utilisateur-profil-update.component';
import { UtilisateurProfilService } from 'app/entities/utilisateur-profil/utilisateur-profil.service';
import { UtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';

describe('Component Tests', () => {
    describe('UtilisateurProfil Management Update Component', () => {
        let comp: UtilisateurProfilUpdateComponent;
        let fixture: ComponentFixture<UtilisateurProfilUpdateComponent>;
        let service: UtilisateurProfilService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurProfilUpdateComponent]
            })
                .overrideTemplate(UtilisateurProfilUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UtilisateurProfilUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurProfilService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurProfil(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurProfil = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new UtilisateurProfil();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.utilisateurProfil = entity;
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
