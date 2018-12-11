/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ProfilActionPKDetailComponent } from 'app/entities/profil-action-pk/profil-action-pk-detail.component';
import { ProfilActionPK } from 'app/shared/model/profil-action-pk.model';

describe('Component Tests', () => {
    describe('ProfilActionPK Management Detail Component', () => {
        let comp: ProfilActionPKDetailComponent;
        let fixture: ComponentFixture<ProfilActionPKDetailComponent>;
        const route = ({ data: of({ profilActionPK: new ProfilActionPK(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ProfilActionPKDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProfilActionPKDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProfilActionPKDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.profilActionPK).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
