/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatDeviseDetailComponent } from 'app/entities/achat-devise/achat-devise-detail.component';
import { AchatDevise } from 'app/shared/model/achat-devise.model';

describe('Component Tests', () => {
    describe('AchatDevise Management Detail Component', () => {
        let comp: AchatDeviseDetailComponent;
        let fixture: ComponentFixture<AchatDeviseDetailComponent>;
        const route = ({ data: of({ achatDevise: new AchatDevise(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatDeviseDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AchatDeviseDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatDeviseDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.achatDevise).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
