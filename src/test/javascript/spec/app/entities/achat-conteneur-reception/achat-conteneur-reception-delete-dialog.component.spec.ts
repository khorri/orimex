/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatConteneurReceptionDeleteDialogComponent } from 'app/entities/achat-conteneur-reception/achat-conteneur-reception-delete-dialog.component';
import { AchatConteneurReceptionService } from 'app/entities/achat-conteneur-reception/achat-conteneur-reception.service';

describe('Component Tests', () => {
    describe('AchatConteneurReception Management Delete Component', () => {
        let comp: AchatConteneurReceptionDeleteDialogComponent;
        let fixture: ComponentFixture<AchatConteneurReceptionDeleteDialogComponent>;
        let service: AchatConteneurReceptionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatConteneurReceptionDeleteDialogComponent]
            })
                .overrideTemplate(AchatConteneurReceptionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatConteneurReceptionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatConteneurReceptionService);
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
