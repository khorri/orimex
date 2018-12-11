/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatFournisseurDeleteDialogComponent } from 'app/entities/achat-fournisseur/achat-fournisseur-delete-dialog.component';
import { AchatFournisseurService } from 'app/entities/achat-fournisseur/achat-fournisseur.service';

describe('Component Tests', () => {
    describe('AchatFournisseur Management Delete Component', () => {
        let comp: AchatFournisseurDeleteDialogComponent;
        let fixture: ComponentFixture<AchatFournisseurDeleteDialogComponent>;
        let service: AchatFournisseurService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatFournisseurDeleteDialogComponent]
            })
                .overrideTemplate(AchatFournisseurDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatFournisseurDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatFournisseurService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
