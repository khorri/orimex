/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatProformaDetailComponent } from 'app/entities/achat-proforma/achat-proforma-detail.component';
import { AchatProforma } from 'app/shared/model/achat-proforma.model';

describe('Component Tests', () => {
    describe('AchatProforma Management Detail Component', () => {
        let comp: AchatProformaDetailComponent;
        let fixture: ComponentFixture<AchatProformaDetailComponent>;
        const route = ({ data: of({ achatProforma: new AchatProforma(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatProformaDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatProformaDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatProformaDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatProforma).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
