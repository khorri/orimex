/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurDepotDetailComponent } from 'app/entities/utilisateur-depot/utilisateur-depot-detail.component';
import { UtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';

describe('Component Tests', () => {
    describe('UtilisateurDepot Management Detail Component', () => {
        let comp: UtilisateurDepotDetailComponent;
        let fixture: ComponentFixture<UtilisateurDepotDetailComponent>;
        const route = ({ data: of({ utilisateurDepot: new UtilisateurDepot(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurDepotDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UtilisateurDepotDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurDepotDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.utilisateurDepot).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
