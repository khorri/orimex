/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleConteneurArrivageDetailComponent } from 'app/entities/achat-article-conteneur-arrivage/achat-article-conteneur-arrivage-detail.component';
import { AchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';

describe('Component Tests', () => {
    describe('AchatArticleConteneurArrivage Management Detail Component', () => {
        let comp: AchatArticleConteneurArrivageDetailComponent;
        let fixture: ComponentFixture<AchatArticleConteneurArrivageDetailComponent>;
        const route = ({ data: of({ achatArticleConteneurArrivage: new AchatArticleConteneurArrivage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleConteneurArrivageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatArticleConteneurArrivageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArticleConteneurArrivageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatArticleConteneurArrivage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
