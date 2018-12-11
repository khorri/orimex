/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { FamilleProduitDetailComponent } from 'app/entities/famille-produit/famille-produit-detail.component';
import { FamilleProduit } from 'app/shared/model/famille-produit.model';

describe('Component Tests', () => {
    describe('FamilleProduit Management Detail Component', () => {
        let comp: FamilleProduitDetailComponent;
        let fixture: ComponentFixture<FamilleProduitDetailComponent>;
        const route = ({ data: of({ familleProduit: new FamilleProduit(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [FamilleProduitDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(FamilleProduitDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FamilleProduitDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.familleProduit).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
