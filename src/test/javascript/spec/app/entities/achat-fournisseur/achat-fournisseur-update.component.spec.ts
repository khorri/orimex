/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { OrimexTestModule } from '../../../test.module';
import { AchatFournisseurUpdateComponent } from 'app/entities/achat-fournisseur/achat-fournisseur-update.component';
import { AchatFournisseurService } from 'app/entities/achat-fournisseur/achat-fournisseur.service';
import { AchatFournisseur } from 'app/shared/model/achat-fournisseur.model';

describe('Component Tests', () => {
    describe('AchatFournisseur Management Update Component', () => {
        let comp: AchatFournisseurUpdateComponent;
        let fixture: ComponentFixture<AchatFournisseurUpdateComponent>;
        let service: AchatFournisseurService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatFournisseurUpdateComponent]
            })
                .overrideTemplate(AchatFournisseurUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AchatFournisseurUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatFournisseurService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatFournisseur(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatFournisseur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AchatFournisseur();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.achatFournisseur = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
