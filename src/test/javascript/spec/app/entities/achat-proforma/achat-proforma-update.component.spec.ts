/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatProformaUpdateComponent } from 'app/entities/achat-proforma/achat-proforma-update.component';
import { AchatProformaService } from 'app/entities/achat-proforma/achat-proforma.service';
import { AchatProforma } from 'app/shared/model/achat-proforma.model';

describe('Component Tests', () => {
    describe('AchatProforma Management Update Component', () => {
        let comp: AchatProformaUpdateComponent;
        let fixture: ComponentFixture<AchatProformaUpdateComponent>;
        let service: AchatProformaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatProformaUpdateComponent]
            })
                .overrideTemplate(AchatProformaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatProformaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatProformaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatProforma(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatProforma = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatProforma();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatProforma = entity;
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
