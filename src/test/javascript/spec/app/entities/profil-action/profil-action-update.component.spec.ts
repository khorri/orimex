/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ProfilActionUpdateComponent } from 'app/entities/profil-action/profil-action-update.component';
import { ProfilActionService } from 'app/entities/profil-action/profil-action.service';
import { ProfilAction } from 'app/shared/model/profil-action.model';

describe('Component Tests', () => {
    describe('ProfilAction Management Update Component', () => {
        let comp: ProfilActionUpdateComponent;
        let fixture: ComponentFixture<ProfilActionUpdateComponent>;
        let service: ProfilActionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ProfilActionUpdateComponent]
            })
                .overrideTemplate(ProfilActionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilActionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilActionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ProfilAction(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.profilAction = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ProfilAction();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.profilAction = entity;
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
