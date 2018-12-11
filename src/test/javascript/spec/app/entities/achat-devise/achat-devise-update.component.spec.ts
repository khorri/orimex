/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatDeviseUpdateComponent } from 'app/entities/achat-devise/achat-devise-update.component';
import { AchatDeviseService } from 'app/entities/achat-devise/achat-devise.service';
import { AchatDevise } from 'app/shared/model/achat-devise.model';

describe('Component Tests', () => {
    describe('AchatDevise Management Update Component', () => {
        let comp: AchatDeviseUpdateComponent;
        let fixture: ComponentFixture<AchatDeviseUpdateComponent>;
        let service: AchatDeviseService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatDeviseUpdateComponent]
            })
                .overrideTemplate(AchatDeviseUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatDeviseUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatDeviseService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatDevise(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatDevise = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatDevise();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatDevise = entity;
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
