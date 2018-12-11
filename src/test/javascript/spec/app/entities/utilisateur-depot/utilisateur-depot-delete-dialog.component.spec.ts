/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurDepotDeleteDialogComponent } from 'app/entities/utilisateur-depot/utilisateur-depot-delete-dialog.component';
import { UtilisateurDepotService } from 'app/entities/utilisateur-depot/utilisateur-depot.service';

describe('Component Tests', () => {
    describe('UtilisateurDepot Management Delete Component', () => {
        let comp: UtilisateurDepotDeleteDialogComponent;
        let fixture: ComponentFixture<UtilisateurDepotDeleteDialogComponent>;
        let service: UtilisateurDepotService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurDepotDeleteDialogComponent]
            })
                .overrideTemplate(UtilisateurDepotDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurDepotDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurDepotService);
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
