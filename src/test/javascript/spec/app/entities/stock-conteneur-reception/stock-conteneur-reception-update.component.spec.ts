/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { StockConteneurReceptionUpdateComponent } from 'app/entities/stock-conteneur-reception/stock-conteneur-reception-update.component';
import { StockConteneurReceptionService } from 'app/entities/stock-conteneur-reception/stock-conteneur-reception.service';
import { StockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';

describe('Component Tests', () => {
    describe('StockConteneurReception Management Update Component', () => {
        let comp: StockConteneurReceptionUpdateComponent;
        let fixture: ComponentFixture<StockConteneurReceptionUpdateComponent>;
        let service: StockConteneurReceptionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockConteneurReceptionUpdateComponent]
            })
                .overrideTemplate(StockConteneurReceptionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StockConteneurReceptionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StockConteneurReceptionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new StockConteneurReception(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.stockConteneurReception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new StockConteneurReception();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.stockConteneurReception = entity;
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
