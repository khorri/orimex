/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatLigneProformaUpdateComponent } from 'app/entities/achat-ligne-proforma/achat-ligne-proforma-update.component';
import { AchatLigneProformaService } from 'app/entities/achat-ligne-proforma/achat-ligne-proforma.service';
import { AchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';

describe('Component Tests', () => {
    describe('AchatLigneProforma Management Update Component', () => {
        let comp: AchatLigneProformaUpdateComponent;
        let fixture: ComponentFixture<AchatLigneProformaUpdateComponent>;
        let service: AchatLigneProformaService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatLigneProformaUpdateComponent]
            })
                .overrideTemplate(AchatLigneProformaUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatLigneProformaUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatLigneProformaService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatLigneProforma(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatLigneProforma = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatLigneProforma();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatLigneProforma = entity;
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
