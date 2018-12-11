/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { OrigineUpdateComponent } from 'app/entities/origine/origine-update.component';
import { OrigineService } from 'app/entities/origine/origine.service';
import { Origine } from 'app/shared/model/origine.model';

describe('Component Tests', () => {
    describe('Origine Management Update Component', () => {
        let comp: OrigineUpdateComponent;
        let fixture: ComponentFixture<OrigineUpdateComponent>;
        let service: OrigineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [OrigineUpdateComponent]
            })
                .overrideTemplate(OrigineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(OrigineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(OrigineService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Origine(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.origine = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Origine();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.origine = entity;
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
