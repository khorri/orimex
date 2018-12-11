/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurDepotPKDeleteDialogComponent } from 'app/entities/utilisateur-depot-pk/utilisateur-depot-pk-delete-dialog.component';
import { UtilisateurDepotPKService } from 'app/entities/utilisateur-depot-pk/utilisateur-depot-pk.service';

describe('Component Tests', () => {
    describe('UtilisateurDepotPK Management Delete Component', () => {
        let comp: UtilisateurDepotPKDeleteDialogComponent;
        let fixture: ComponentFixture<UtilisateurDepotPKDeleteDialogComponent>;
        let service: UtilisateurDepotPKService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurDepotPKDeleteDialogComponent]
            })
                .overrideTemplate(UtilisateurDepotPKDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurDepotPKDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurDepotPKService);
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
