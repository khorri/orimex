/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { CasseDetailComponent } from 'app/entities/casse/casse-detail.component';
import { Casse } from 'app/shared/model/casse.model';

describe('Component Tests', () => {
    describe('Casse Management Detail Component', () => {
        let comp: CasseDetailComponent;
        let fixture: ComponentFixture<CasseDetailComponent>;
        const route = ({ data: of({ casse: new Casse(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CasseDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CasseDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CasseDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.casse).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
