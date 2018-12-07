/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatArticleConteneurArrivageDeleteDialogComponent } from 'app/entities/achat-article-conteneur-arrivage/achat-article-conteneur-arrivage-delete-dialog.component';
import { AchatArticleConteneurArrivageService } from 'app/entities/achat-article-conteneur-arrivage/achat-article-conteneur-arrivage.service';

describe('Component Tests', () => {
    describe('AchatArticleConteneurArrivage Management Delete Component', () => {
        let comp: AchatArticleConteneurArrivageDeleteDialogComponent;
        let fixture: ComponentFixture<AchatArticleConteneurArrivageDeleteDialogComponent>;
        let service: AchatArticleConteneurArrivageService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatArticleConteneurArrivageDeleteDialogComponent]
            })
                .overrideTemplate(AchatArticleConteneurArrivageDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatArticleConteneurArrivageDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatArticleConteneurArrivageService);
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
