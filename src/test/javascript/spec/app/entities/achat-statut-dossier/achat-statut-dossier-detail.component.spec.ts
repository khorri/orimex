/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatStatutDossierDetailComponent } from 'app/entities/achat-statut-dossier/achat-statut-dossier-detail.component';
import { AchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';

describe('Component Tests', () => {
    describe('AchatStatutDossier Management Detail Component', () => {
        let comp: AchatStatutDossierDetailComponent;
        let fixture: ComponentFixture<AchatStatutDossierDetailComponent>;
        const route = ({ data: of({ achatStatutDossier: new AchatStatutDossier(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatStatutDossierDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatStatutDossierDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatStatutDossierDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatStatutDossier).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
