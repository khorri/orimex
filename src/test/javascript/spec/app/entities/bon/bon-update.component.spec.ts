/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { BonUpdateComponent } from 'app/entities/bon/bon-update.component';
import { BonService } from 'app/entities/bon/bon.service';
import { Bon } from 'app/shared/model/bon.model';

describe('Component Tests', () => {
    describe('Bon Management Update Component', () => {
        let comp: BonUpdateComponent;
        let fixture: ComponentFixture<BonUpdateComponent>;
        let service: BonService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [BonUpdateComponent]
            })
                .overrideTemplate(BonUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BonUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BonService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bon(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bon = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Bon();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.bon = entity;
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
