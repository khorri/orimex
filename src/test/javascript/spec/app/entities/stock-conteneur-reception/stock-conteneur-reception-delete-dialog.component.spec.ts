/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { StockConteneurReceptionDeleteDialogComponent } from 'app/entities/stock-conteneur-reception/stock-conteneur-reception-delete-dialog.component';
import { StockConteneurReceptionService } from 'app/entities/stock-conteneur-reception/stock-conteneur-reception.service';

describe('Component Tests', () => {
    describe('StockConteneurReception Management Delete Component', () => {
        let comp: StockConteneurReceptionDeleteDialogComponent;
        let fixture: ComponentFixture<StockConteneurReceptionDeleteDialogComponent>;
        let service: StockConteneurReceptionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockConteneurReceptionDeleteDialogComponent]
            })
                .overrideTemplate(StockConteneurReceptionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StockConteneurReceptionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StockConteneurReceptionService);
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
