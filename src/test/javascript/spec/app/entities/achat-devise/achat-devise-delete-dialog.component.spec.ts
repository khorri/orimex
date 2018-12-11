/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatDeviseDeleteDialogComponent } from 'app/entities/achat-devise/achat-devise-delete-dialog.component';
import { AchatDeviseService } from 'app/entities/achat-devise/achat-devise.service';

describe('Component Tests', () => {
    describe('AchatDevise Management Delete Component', () => {
        let comp: AchatDeviseDeleteDialogComponent;
        let fixture: ComponentFixture<AchatDeviseDeleteDialogComponent>;
        let service: AchatDeviseService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatDeviseDeleteDialogComponent]
            })
                .overrideTemplate(AchatDeviseDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatDeviseDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatDeviseService);
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
