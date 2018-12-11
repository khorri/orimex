/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurProfilDetailComponent } from 'app/entities/utilisateur-profil/utilisateur-profil-detail.component';
import { UtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';

describe('Component Tests', () => {
    describe('UtilisateurProfil Management Detail Component', () => {
        let comp: UtilisateurProfilDetailComponent;
        let fixture: ComponentFixture<UtilisateurProfilDetailComponent>;
        const route = ({ data: of({ utilisateurProfil: new UtilisateurProfil(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurProfilDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UtilisateurProfilDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurProfilDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.utilisateurProfil).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
