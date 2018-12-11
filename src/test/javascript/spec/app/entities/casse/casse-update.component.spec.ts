/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { CasseUpdateComponent } from 'app/entities/casse/casse-update.component';
import { CasseService } from 'app/entities/casse/casse.service';
import { Casse } from 'app/shared/model/casse.model';

describe('Component Tests', () => {
    describe('Casse Management Update Component', () => {
        let comp: CasseUpdateComponent;
        let fixture: ComponentFixture<CasseUpdateComponent>;
        let service: CasseService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CasseUpdateComponent]
            })
                .overrideTemplate(CasseUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CasseUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CasseService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Casse(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.casse = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Casse();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.casse = entity;
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
