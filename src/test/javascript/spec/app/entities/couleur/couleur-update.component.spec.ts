/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { CouleurUpdateComponent } from 'app/entities/couleur/couleur-update.component';
import { CouleurService } from 'app/entities/couleur/couleur.service';
import { Couleur } from 'app/shared/model/couleur.model';

describe('Component Tests', () => {
    describe('Couleur Management Update Component', () => {
        let comp: CouleurUpdateComponent;
        let fixture: ComponentFixture<CouleurUpdateComponent>;
        let service: CouleurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CouleurUpdateComponent]
            })
                .overrideTemplate(CouleurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CouleurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CouleurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Couleur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.couleur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Couleur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.couleur = entity;
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
