/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatConteneurArrivageDetailComponent } from 'app/entities/achat-conteneur-arrivage/achat-conteneur-arrivage-detail.component';
import { AchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';

describe('Component Tests', () => {
    describe('AchatConteneurArrivage Management Detail Component', () => {
        let comp: AchatConteneurArrivageDetailComponent;
        let fixture: ComponentFixture<AchatConteneurArrivageDetailComponent>;
        const route = ({ data: of({ achatConteneurArrivage: new AchatConteneurArrivage(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatConteneurArrivageDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatConteneurArrivageDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatConteneurArrivageDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatConteneurArrivage).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
