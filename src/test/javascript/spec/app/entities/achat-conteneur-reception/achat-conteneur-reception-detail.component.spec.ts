/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatConteneurReceptionDetailComponent } from 'app/entities/achat-conteneur-reception/achat-conteneur-reception-detail.component';
import { AchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';

describe('Component Tests', () => {
    describe('AchatConteneurReception Management Detail Component', () => {
        let comp: AchatConteneurReceptionDetailComponent;
        let fixture: ComponentFixture<AchatConteneurReceptionDetailComponent>;
        const route = ({ data: of({ achatConteneurReception: new AchatConteneurReception(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatConteneurReceptionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatConteneurReceptionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatConteneurReceptionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatConteneurReception).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
