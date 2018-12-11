/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatBanqueDetailComponent } from 'app/entities/achat-banque/achat-banque-detail.component';
import { AchatBanque } from 'app/shared/model/achat-banque.model';

describe('Component Tests', () => {
    describe('AchatBanque Management Detail Component', () => {
        let comp: AchatBanqueDetailComponent;
        let fixture: ComponentFixture<AchatBanqueDetailComponent>;
        const route = ({ data: of({ achatBanque: new AchatBanque(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatBanqueDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatBanqueDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatBanqueDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatBanque).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
