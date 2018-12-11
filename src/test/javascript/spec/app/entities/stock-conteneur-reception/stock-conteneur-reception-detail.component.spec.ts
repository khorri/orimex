/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { StockConteneurReceptionDetailComponent } from 'app/entities/stock-conteneur-reception/stock-conteneur-reception-detail.component';
import { StockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';

describe('Component Tests', () => {
    describe('StockConteneurReception Management Detail Component', () => {
        let comp: StockConteneurReceptionDetailComponent;
        let fixture: ComponentFixture<StockConteneurReceptionDetailComponent>;
        const route = ({ data: of({ stockConteneurReception: new StockConteneurReception(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockConteneurReceptionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StockConteneurReceptionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StockConteneurReceptionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.stockConteneurReception).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
