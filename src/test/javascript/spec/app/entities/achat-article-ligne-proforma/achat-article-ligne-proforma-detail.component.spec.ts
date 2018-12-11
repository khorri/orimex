/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleLigneProformaDetailComponent } from 'app/entities/achat-article-ligne-proforma/achat-article-ligne-proforma-detail.component';
import { AchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';

describe('Component Tests', () => {
    describe('AchatArticleLigneProforma Management Detail Component', () => {
        let comp: AchatArticleLigneProformaDetailComponent;
        let fixture: ComponentFixture<AchatArticleLigneProformaDetailComponent>;
        const route = ({ data: of({ achatArticleLigneProforma: new AchatArticleLigneProforma(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleLigneProformaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatArticleLigneProformaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArticleLigneProformaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatArticleLigneProforma).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
