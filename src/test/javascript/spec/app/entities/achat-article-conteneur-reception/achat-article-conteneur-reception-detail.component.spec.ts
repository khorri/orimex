/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleConteneurReceptionDetailComponent } from 'app/entities/achat-article-conteneur-reception/achat-article-conteneur-reception-detail.component';
import { AchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';

describe('Component Tests', () => {
    describe('AchatArticleConteneurReception Management Detail Component', () => {
        let comp: AchatArticleConteneurReceptionDetailComponent;
        let fixture: ComponentFixture<AchatArticleConteneurReceptionDetailComponent>;
        const route = ({ data: of({ achatArticleConteneurReception: new AchatArticleConteneurReception(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleConteneurReceptionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatArticleConteneurReceptionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArticleConteneurReceptionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatArticleConteneurReception).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
