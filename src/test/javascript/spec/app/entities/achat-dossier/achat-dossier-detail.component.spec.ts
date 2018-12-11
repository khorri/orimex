/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatDossierDetailComponent } from 'app/entities/achat-dossier/achat-dossier-detail.component';
import { AchatDossier } from 'app/shared/model/achat-dossier.model';

describe('Component Tests', () => {
    describe('AchatDossier Management Detail Component', () => {
        let comp: AchatDossierDetailComponent;
        let fixture: ComponentFixture<AchatDossierDetailComponent>;
        const route = ({ data: of({ achatDossier: new AchatDossier(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatDossierDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatDossierDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatDossierDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatDossier).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
