/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ReceptionUpdateComponent } from 'app/entities/reception/reception-update.component';
import { ReceptionService } from 'app/entities/reception/reception.service';
import { Reception } from 'app/shared/model/reception.model';

describe('Component Tests', () => {
    describe('Reception Management Update Component', () => {
        let comp: ReceptionUpdateComponent;
        let fixture: ComponentFixture<ReceptionUpdateComponent>;
        let service: ReceptionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ReceptionUpdateComponent]
            })
                .overrideTemplate(ReceptionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ReceptionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ReceptionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Reception(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.reception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Reception();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.reception = entity;
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
