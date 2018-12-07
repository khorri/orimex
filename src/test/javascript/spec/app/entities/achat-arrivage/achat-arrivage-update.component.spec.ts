/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArrivageUpdateComponent } from 'app/entities/achat-arrivage/achat-arrivage-update.component';
import { AchatArrivageService } from 'app/entities/achat-arrivage/achat-arrivage.service';
import { AchatArrivage } from 'app/shared/model/achat-arrivage.model';

describe('Component Tests', () => {
    describe('AchatArrivage Management Update Component', () => {
        let comp: AchatArrivageUpdateComponent;
        let fixture: ComponentFixture<AchatArrivageUpdateComponent>;
        let service: AchatArrivageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArrivageUpdateComponent]
            })
                .overrideTemplate(AchatArrivageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatArrivageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArrivageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArrivage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArrivage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatArrivage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatArrivage = entity;
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
