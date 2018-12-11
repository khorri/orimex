/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ConteneurUpdateComponent } from 'app/entities/conteneur/conteneur-update.component';
import { ConteneurService } from 'app/entities/conteneur/conteneur.service';
import { Conteneur } from 'app/shared/model/conteneur.model';

describe('Component Tests', () => {
    describe('Conteneur Management Update Component', () => {
        let comp: ConteneurUpdateComponent;
        let fixture: ComponentFixture<ConteneurUpdateComponent>;
        let service: ConteneurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ConteneurUpdateComponent]
            })
                .overrideTemplate(ConteneurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ConteneurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConteneurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Conteneur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conteneur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Conteneur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.conteneur = entity;
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
