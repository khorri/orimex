/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { UtilisateurProfilDeleteDialogComponent } from 'app/entities/utilisateur-profil/utilisateur-profil-delete-dialog.component';
import { UtilisateurProfilService } from 'app/entities/utilisateur-profil/utilisateur-profil.service';

describe('Component Tests', () => {
    describe('UtilisateurProfil Management Delete Component', () => {
        let comp: UtilisateurProfilDeleteDialogComponent;
        let fixture: ComponentFixture<UtilisateurProfilDeleteDialogComponent>;
        let service: UtilisateurProfilService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [UtilisateurProfilDeleteDialogComponent]
            })
                .overrideTemplate(UtilisateurProfilDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UtilisateurProfilDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UtilisateurProfilService);
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
