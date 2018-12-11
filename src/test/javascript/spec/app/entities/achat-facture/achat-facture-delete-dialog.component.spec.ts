/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatFactureDeleteDialogComponent } from 'app/entities/achat-facture/achat-facture-delete-dialog.component';
import { AchatFactureService } from 'app/entities/achat-facture/achat-facture.service';

describe('Component Tests', () => {
    describe('AchatFacture Management Delete Component', () => {
        let comp: AchatFactureDeleteDialogComponent;
        let fixture: ComponentFixture<AchatFactureDeleteDialogComponent>;
        let service: AchatFactureService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatFactureDeleteDialogComponent]
            })
                .overrideTemplate(AchatFactureDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatFactureDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatFactureService);
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
