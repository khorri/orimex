/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatBanqueUpdateComponent } from 'app/entities/achat-banque/achat-banque-update.component';
import { AchatBanqueService } from 'app/entities/achat-banque/achat-banque.service';
import { AchatBanque } from 'app/shared/model/achat-banque.model';

describe('Component Tests', () => {
    describe('AchatBanque Management Update Component', () => {
        let comp: AchatBanqueUpdateComponent;
        let fixture: ComponentFixture<AchatBanqueUpdateComponent>;
        let service: AchatBanqueService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatBanqueUpdateComponent]
            })
                .overrideTemplate(AchatBanqueUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatBanqueUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatBanqueService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatBanque(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatBanque = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatBanque();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatBanque = entity;
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
