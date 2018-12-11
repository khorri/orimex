/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { CouleurDetailComponent } from 'app/entities/couleur/couleur-detail.component';
import { Couleur } from 'app/shared/model/couleur.model';

describe('Component Tests', () => {
    describe('Couleur Management Detail Component', () => {
        let comp: CouleurDetailComponent;
        let fixture: ComponentFixture<CouleurDetailComponent>;
        const route = ({ data: of({ couleur: new Couleur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CouleurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CouleurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CouleurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.couleur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
