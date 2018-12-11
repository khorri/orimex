/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurDepotPKDetailComponent } from 'app/entities/utilisateur-depot-pk/utilisateur-depot-pk-detail.component';
import { UtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';

describe('Component Tests', () => {
    describe('UtilisateurDepotPK Management Detail Component', () => {
        let comp: UtilisateurDepotPKDetailComponent;
        let fixture: ComponentFixture<UtilisateurDepotPKDetailComponent>;
        const route = ({ data: of({ utilisateurDepotPK: new UtilisateurDepotPK(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurDepotPKDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UtilisateurDepotPKDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurDepotPKDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.utilisateurDepotPK).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
