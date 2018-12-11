/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatDossierDeleteDialogComponent } from 'app/entities/achat-dossier/achat-dossier-delete-dialog.component';
import { AchatDossierService } from 'app/entities/achat-dossier/achat-dossier.service';

describe('Component Tests', () => {
    describe('AchatDossier Management Delete Component', () => {
        let comp: AchatDossierDeleteDialogComponent;
        let fixture: ComponentFixture<AchatDossierDeleteDialogComponent>;
        let service: AchatDossierService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatDossierDeleteDialogComponent]
            })
                .overrideTemplate(AchatDossierDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatDossierDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatDossierService);
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
