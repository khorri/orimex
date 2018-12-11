/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ReceptionDetailComponent } from 'app/entities/reception/reception-detail.component';
import { Reception } from 'app/shared/model/reception.model';

describe('Component Tests', () => {
    describe('Reception Management Detail Component', () => {
        let comp: ReceptionDetailComponent;
        let fixture: ComponentFixture<ReceptionDetailComponent>;
        const route = ({ data: of({ reception: new Reception(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ReceptionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ReceptionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ReceptionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.reception).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
