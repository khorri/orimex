/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { RecuperationUpdateComponent } from 'app/entities/recuperation/recuperation-update.component';
import { RecuperationService } from 'app/entities/recuperation/recuperation.service';
import { Recuperation } from 'app/shared/model/recuperation.model';

describe('Component Tests', () => {
    describe('Recuperation Management Update Component', () => {
        let comp: RecuperationUpdateComponent;
        let fixture: ComponentFixture<RecuperationUpdateComponent>;
        let service: RecuperationService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [RecuperationUpdateComponent]
            })
                .overrideTemplate(RecuperationUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RecuperationUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecuperationService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Recuperation(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.recuperation = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Recuperation();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.recuperation = entity;
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
