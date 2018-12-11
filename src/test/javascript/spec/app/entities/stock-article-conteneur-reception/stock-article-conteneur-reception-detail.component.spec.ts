/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { StockArticleConteneurReceptionDetailComponent } from 'app/entities/stock-article-conteneur-reception/stock-article-conteneur-reception-detail.component';
import { StockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';

describe('Component Tests', () => {
    describe('StockArticleConteneurReception Management Detail Component', () => {
        let comp: StockArticleConteneurReceptionDetailComponent;
        let fixture: ComponentFixture<StockArticleConteneurReceptionDetailComponent>;
        const route = ({ data: of({ stockArticleConteneurReception: new StockArticleConteneurReception(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockArticleConteneurReceptionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StockArticleConteneurReceptionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StockArticleConteneurReceptionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.stockArticleConteneurReception).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
