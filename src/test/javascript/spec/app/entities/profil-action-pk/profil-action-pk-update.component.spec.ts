/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ProfilActionPKUpdateComponent } from 'app/entities/profil-action-pk/profil-action-pk-update.component';
import { ProfilActionPKService } from 'app/entities/profil-action-pk/profil-action-pk.service';
import { ProfilActionPK } from 'app/shared/model/profil-action-pk.model';

describe('Component Tests', () => {
    describe('ProfilActionPK Management Update Component', () => {
        let comp: ProfilActionPKUpdateComponent;
        let fixture: ComponentFixture<ProfilActionPKUpdateComponent>;
        let service: ProfilActionPKService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ProfilActionPKUpdateComponent]
            })
                .overrideTemplate(ProfilActionPKUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProfilActionPKUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProfilActionPKService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new ProfilActionPK(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.profilActionPK = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new ProfilActionPK();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.profilActionPK = entity;
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
