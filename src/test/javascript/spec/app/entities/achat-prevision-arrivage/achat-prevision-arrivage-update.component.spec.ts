/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatPrevisionArrivageUpdateComponent } from 'app/entities/achat-prevision-arrivage/achat-prevision-arrivage-update.component';
import { AchatPrevisionArrivageService } from 'app/entities/achat-prevision-arrivage/achat-prevision-arrivage.service';
import { AchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';

describe('Component Tests', () => {
    describe('AchatPrevisionArrivage Management Update Component', () => {
        let comp: AchatPrevisionArrivageUpdateComponent;
        let fixture: ComponentFixture<AchatPrevisionArrivageUpdateComponent>;
        let service: AchatPrevisionArrivageService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatPrevisionArrivageUpdateComponent]
            })
                .overrideTemplate(AchatPrevisionArrivageUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatPrevisionArrivageUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatPrevisionArrivageService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatPrevisionArrivage(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatPrevisionArrivage = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatPrevisionArrivage();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatPrevisionArrivage = entity;
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
