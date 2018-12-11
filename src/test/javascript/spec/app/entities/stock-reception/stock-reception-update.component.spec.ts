/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { StockReceptionUpdateComponent } from 'app/entities/stock-reception/stock-reception-update.component';
import { StockReceptionService } from 'app/entities/stock-reception/stock-reception.service';
import { StockReception } from 'app/shared/model/stock-reception.model';

describe('Component Tests', () => {
    describe('StockReception Management Update Component', () => {
        let comp: StockReceptionUpdateComponent;
        let fixture: ComponentFixture<StockReceptionUpdateComponent>;
        let service: StockReceptionService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockReceptionUpdateComponent]
            })
                .overrideTemplate(StockReceptionUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StockReceptionUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StockReceptionService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new StockReception(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.stockReception = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new StockReception();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.stockReception = entity;
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
