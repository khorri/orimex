/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { ConteneurDetailComponent } from 'app/entities/conteneur/conteneur-detail.component';
import { Conteneur } from 'app/shared/model/conteneur.model';

describe('Component Tests', () => {
    describe('Conteneur Management Detail Component', () => {
        let comp: ConteneurDetailComponent;
        let fixture: ComponentFixture<ConteneurDetailComponent>;
        const route = ({ data: of({ conteneur: new Conteneur(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ConteneurDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ConteneurDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConteneurDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.conteneur).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
