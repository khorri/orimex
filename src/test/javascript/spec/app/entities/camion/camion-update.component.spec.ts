/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { CamionUpdateComponent } from 'app/entities/camion/camion-update.component';
import { CamionService } from 'app/entities/camion/camion.service';
import { Camion } from 'app/shared/model/camion.model';

describe('Component Tests', () => {
    describe('Camion Management Update Component', () => {
        let comp: CamionUpdateComponent;
        let fixture: ComponentFixture<CamionUpdateComponent>;
        let service: CamionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CamionUpdateComponent]
            })
                .overrideTemplate(CamionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CamionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CamionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Camion(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.camion = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Camion();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.camion = entity;
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
