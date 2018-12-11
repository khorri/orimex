/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { StockReceptionDetailComponent } from 'app/entities/stock-reception/stock-reception-detail.component';
import { StockReception } from 'app/shared/model/stock-reception.model';

describe('Component Tests', () => {
    describe('StockReception Management Detail Component', () => {
        let comp: StockReceptionDetailComponent;
        let fixture: ComponentFixture<StockReceptionDetailComponent>;
        const route = ({ data: of({ stockReception: new StockReception(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockReceptionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StockReceptionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StockReceptionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.stockReception).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
