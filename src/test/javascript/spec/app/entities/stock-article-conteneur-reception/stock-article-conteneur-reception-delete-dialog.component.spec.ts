/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { StockArticleConteneurReceptionDeleteDialogComponent } from 'app/entities/stock-article-conteneur-reception/stock-article-conteneur-reception-delete-dialog.component';
import { StockArticleConteneurReceptionService } from 'app/entities/stock-article-conteneur-reception/stock-article-conteneur-reception.service';

describe('Component Tests', () => {
    describe('StockArticleConteneurReception Management Delete Component', () => {
        let comp: StockArticleConteneurReceptionDeleteDialogComponent;
        let fixture: ComponentFixture<StockArticleConteneurReceptionDeleteDialogComponent>;
        let service: StockArticleConteneurReceptionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [StockArticleConteneurReceptionDeleteDialogComponent]
            })
                .overrideTemplate(StockArticleConteneurReceptionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StockArticleConteneurReceptionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StockArticleConteneurReceptionService);
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
