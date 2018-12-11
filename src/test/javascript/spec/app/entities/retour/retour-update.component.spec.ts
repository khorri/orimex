/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { RetourUpdateComponent } from 'app/entities/retour/retour-update.component';
import { RetourService } from 'app/entities/retour/retour.service';
import { Retour } from 'app/shared/model/retour.model';

describe('Component Tests', () => {
    describe('Retour Management Update Component', () => {
        let comp: RetourUpdateComponent;
        let fixture: ComponentFixture<RetourUpdateComponent>;
        let service: RetourService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [RetourUpdateComponent]
            })
                .overrideTemplate(RetourUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RetourUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RetourService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Retour(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.retour = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Retour();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.retour = entity;
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
