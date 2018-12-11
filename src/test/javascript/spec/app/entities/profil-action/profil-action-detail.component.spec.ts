/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ProfilActionDetailComponent } from 'app/entities/profil-action/profil-action-detail.component';
import { ProfilAction } from 'app/shared/model/profil-action.model';

describe('Component Tests', () => {
    describe('ProfilAction Management Detail Component', () => {
        let comp: ProfilActionDetailComponent;
        let fixture: ComponentFixture<ProfilActionDetailComponent>;
        const route = ({ data: of({ profilAction: new ProfilAction(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ProfilActionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfilActionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilActionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.profilAction).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
