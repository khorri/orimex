/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleLigneProformaDeleteDialogComponent } from 'app/entities/achat-article-ligne-proforma/achat-article-ligne-proforma-delete-dialog.component';
import { AchatArticleLigneProformaService } from 'app/entities/achat-article-ligne-proforma/achat-article-ligne-proforma.service';

describe('Component Tests', () => {
    describe('AchatArticleLigneProforma Management Delete Component', () => {
        let comp: AchatArticleLigneProformaDeleteDialogComponent;
        let fixture: ComponentFixture<AchatArticleLigneProformaDeleteDialogComponent>;
        let service: AchatArticleLigneProformaService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleLigneProformaDeleteDialogComponent]
            })
                .overrideTemplate(AchatArticleLigneProformaDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArticleLigneProformaDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArticleLigneProformaService);
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
