/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatPrevisionArrivageDetailComponent } from 'app/entities/achat-prevision-arrivage/achat-prevision-arrivage-detail.component';
import { AchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';

describe('Component Tests', () => {
    describe('AchatPrevisionArrivage Management Detail Component', () => {
        let comp: AchatPrevisionArrivageDetailComponent;
        let fixture: ComponentFixture<AchatPrevisionArrivageDetailComponent>;
        const route = ({ data: of({ achatPrevisionArrivage: new AchatPrevisionArrivage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatPrevisionArrivageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatPrevisionArrivageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatPrevisionArrivageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatPrevisionArrivage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
