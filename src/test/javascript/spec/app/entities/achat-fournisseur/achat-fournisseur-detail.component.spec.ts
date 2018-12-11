/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatFournisseurDetailComponent } from 'app/entities/achat-fournisseur/achat-fournisseur-detail.component';
import { AchatFournisseur } from 'app/shared/model/achat-fournisseur.model';

describe('Component Tests', () => {
    describe('AchatFournisseur Management Detail Component', () => {
        let comp: AchatFournisseurDetailComponent;
        let fixture: ComponentFixture<AchatFournisseurDetailComponent>;
        const route = ({ data: of({ achatFournisseur: new AchatFournisseur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatFournisseurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatFournisseurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatFournisseurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatFournisseur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
