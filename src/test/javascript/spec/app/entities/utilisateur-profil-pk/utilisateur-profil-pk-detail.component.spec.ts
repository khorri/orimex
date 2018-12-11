/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurProfilPKDetailComponent } from 'app/entities/utilisateur-profil-pk/utilisateur-profil-pk-detail.component';
import { UtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';

describe('Component Tests', () => {
    describe('UtilisateurProfilPK Management Detail Component', () => {
        let comp: UtilisateurProfilPKDetailComponent;
        let fixture: ComponentFixture<UtilisateurProfilPKDetailComponent>;
        const route = ({ data: of({ utilisateurProfilPK: new UtilisateurProfilPK(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurProfilPKDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UtilisateurProfilPKDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurProfilPKDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.utilisateurProfilPK).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
