/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { TypeBonDetailComponent } from 'app/entities/type-bon/type-bon-detail.component';
import { TypeBon } from 'app/shared/model/type-bon.model';

describe('Component Tests', () => {
    describe('TypeBon Management Detail Component', () => {
        let comp: TypeBonDetailComponent;
        let fixture: ComponentFixture<TypeBonDetailComponent>;
        const route = ({ data: of({ typeBon: new TypeBon(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [TypeBonDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TypeBonDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeBonDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.typeBon).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
