/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { CamionDetailComponent } from 'app/entities/camion/camion-detail.component';
import { Camion } from 'app/shared/model/camion.model';

describe('Component Tests', () => {
    describe('Camion Management Detail Component', () => {
        let comp: CamionDetailComponent;
        let fixture: ComponentFixture<CamionDetailComponent>;
        const route = ({ data: of({ camion: new Camion(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CamionDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CamionDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CamionDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.camion).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
