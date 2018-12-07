/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatArrivageDetailComponent } from 'app/entities/achat-arrivage/achat-arrivage-detail.component';
import { AchatArrivage } from 'app/shared/model/achat-arrivage.model';

describe('Component Tests', () => {
    describe('AchatArrivage Management Detail Component', () => {
        let comp: AchatArrivageDetailComponent;
        let fixture: ComponentFixture<AchatArrivageDetailComponent>;
        const route = ({ data: of({ achatArrivage: new AchatArrivage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArrivageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatArrivageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArrivageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatArrivage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
