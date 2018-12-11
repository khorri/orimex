/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleConteneurReceptionDeleteDialogComponent } from 'app/entities/achat-article-conteneur-reception/achat-article-conteneur-reception-delete-dialog.component';
import { AchatArticleConteneurReceptionService } from 'app/entities/achat-article-conteneur-reception/achat-article-conteneur-reception.service';

describe('Component Tests', () => {
    describe('AchatArticleConteneurReception Management Delete Component', () => {
        let comp: AchatArticleConteneurReceptionDeleteDialogComponent;
        let fixture: ComponentFixture<AchatArticleConteneurReceptionDeleteDialogComponent>;
        let service: AchatArticleConteneurReceptionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleConteneurReceptionDeleteDialogComponent]
            })
                .overrideTemplate(AchatArticleConteneurReceptionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArticleConteneurReceptionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArticleConteneurReceptionService);
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
