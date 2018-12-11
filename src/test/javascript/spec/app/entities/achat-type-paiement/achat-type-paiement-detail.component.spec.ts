/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatTypePaiementDetailComponent } from 'app/entities/achat-type-paiement/achat-type-paiement-detail.component';
import { AchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';

describe('Component Tests', () => {
    describe('AchatTypePaiement Management Detail Component', () => {
        let comp: AchatTypePaiementDetailComponent;
        let fixture: ComponentFixture<AchatTypePaiementDetailComponent>;
        const route = ({ data: of({ achatTypePaiement: new AchatTypePaiement(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatTypePaiementDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatTypePaiementDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatTypePaiementDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatTypePaiement).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
