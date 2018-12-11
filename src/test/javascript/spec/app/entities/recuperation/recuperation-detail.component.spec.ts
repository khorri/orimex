/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { RecuperationDetailComponent } from 'app/entities/recuperation/recuperation-detail.component';
import { Recuperation } from 'app/shared/model/recuperation.model';

describe('Component Tests', () => {
    describe('Recuperation Management Detail Component', () => {
        let comp: RecuperationDetailComponent;
        let fixture: ComponentFixture<RecuperationDetailComponent>;
        const route = ({ data: of({ recuperation: new Recuperation(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [RecuperationDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(RecuperationDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RecuperationDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.recuperation).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
