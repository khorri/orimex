/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { BonDetailComponent } from 'app/entities/bon/bon-detail.component';
import { Bon } from 'app/shared/model/bon.model';

describe('Component Tests', () => {
    describe('Bon Management Detail Component', () => {
        let comp: BonDetailComponent;
        let fixture: ComponentFixture<BonDetailComponent>;
        const route = ({ data: of({ bon: new Bon(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [BonDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BonDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BonDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.bon).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
