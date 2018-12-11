/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { SortieDetailComponent } from 'app/entities/sortie/sortie-detail.component';
import { Sortie } from 'app/shared/model/sortie.model';

describe('Component Tests', () => {
    describe('Sortie Management Detail Component', () => {
        let comp: SortieDetailComponent;
        let fixture: ComponentFixture<SortieDetailComponent>;
        const route = ({ data: of({ sortie: new Sortie(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [SortieDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SortieDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SortieDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sortie).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
