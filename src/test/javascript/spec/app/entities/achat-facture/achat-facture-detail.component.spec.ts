/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatFactureDetailComponent } from 'app/entities/achat-facture/achat-facture-detail.component';
import { AchatFacture } from 'app/shared/model/achat-facture.model';

describe('Component Tests', () => {
    describe('AchatFacture Management Detail Component', () => {
        let comp: AchatFactureDetailComponent;
        let fixture: ComponentFixture<AchatFactureDetailComponent>;
        const route = ({ data: of({ achatFacture: new AchatFacture(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatFactureDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatFactureDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatFactureDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatFacture).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
