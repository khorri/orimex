/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { RetourDetailComponent } from 'app/entities/retour/retour-detail.component';
import { Retour } from 'app/shared/model/retour.model';

describe('Component Tests', () => {
    describe('Retour Management Detail Component', () => {
        let comp: RetourDetailComponent;
        let fixture: ComponentFixture<RetourDetailComponent>;
        const route = ({ data: of({ retour: new Retour(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [RetourDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RetourDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RetourDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.retour).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
