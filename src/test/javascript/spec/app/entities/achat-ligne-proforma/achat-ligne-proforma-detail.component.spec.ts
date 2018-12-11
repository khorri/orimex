/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatLigneProformaDetailComponent } from 'app/entities/achat-ligne-proforma/achat-ligne-proforma-detail.component';
import { AchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';

describe('Component Tests', () => {
    describe('AchatLigneProforma Management Detail Component', () => {
        let comp: AchatLigneProformaDetailComponent;
        let fixture: ComponentFixture<AchatLigneProformaDetailComponent>;
        const route = ({ data: of({ achatLigneProforma: new AchatLigneProforma(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatLigneProformaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatLigneProformaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatLigneProformaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatLigneProforma).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
