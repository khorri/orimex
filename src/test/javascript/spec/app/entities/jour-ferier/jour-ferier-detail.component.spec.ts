/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { JourFerierDetailComponent } from 'app/entities/jour-ferier/jour-ferier-detail.component';
import { JourFerier } from 'app/shared/model/jour-ferier.model';

describe('Component Tests', () => {
    describe('JourFerier Management Detail Component', () => {
        let comp: JourFerierDetailComponent;
        let fixture: ComponentFixture<JourFerierDetailComponent>;
        const route = ({ data: of({ jourFerier: new JourFerier(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [JourFerierDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(JourFerierDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JourFerierDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.jourFerier).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
