/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatLigneProformaDeleteDialogComponent } from 'app/entities/achat-ligne-proforma/achat-ligne-proforma-delete-dialog.component';
import { AchatLigneProformaService } from 'app/entities/achat-ligne-proforma/achat-ligne-proforma.service';

describe('Component Tests', () => {
    describe('AchatLigneProforma Management Delete Component', () => {
        let comp: AchatLigneProformaDeleteDialogComponent;
        let fixture: ComponentFixture<AchatLigneProformaDeleteDialogComponent>;
        let service: AchatLigneProformaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatLigneProformaDeleteDialogComponent]
            })
                .overrideTemplate(AchatLigneProformaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatLigneProformaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatLigneProformaService);
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
