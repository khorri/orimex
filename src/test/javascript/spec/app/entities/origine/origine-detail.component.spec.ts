/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { OrigineDetailComponent } from 'app/entities/origine/origine-detail.component';
import { Origine } from 'app/shared/model/origine.model';

describe('Component Tests', () => {
    describe('Origine Management Detail Component', () => {
        let comp: OrigineDetailComponent;
        let fixture: ComponentFixture<OrigineDetailComponent>;
        const route = ({ data: of({ origine: new Origine(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [OrigineDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(OrigineDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(OrigineDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.origine).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
