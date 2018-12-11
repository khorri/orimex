/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { JourFerierUpdateComponent } from 'app/entities/jour-ferier/jour-ferier-update.component';
import { JourFerierService } from 'app/entities/jour-ferier/jour-ferier.service';
import { JourFerier } from 'app/shared/model/jour-ferier.model';

describe('Component Tests', () => {
    describe('JourFerier Management Update Component', () => {
        let comp: JourFerierUpdateComponent;
        let fixture: ComponentFixture<JourFerierUpdateComponent>;
        let service: JourFerierService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [JourFerierUpdateComponent]
            })
                .overrideTemplate(JourFerierUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(JourFerierUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JourFerierService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new JourFerier(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.jourFerier = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new JourFerier();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.jourFerier = entity;
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
