/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { AchatConteneurArrivageDeleteDialogComponent } from 'app/entities/achat-conteneur-arrivage/achat-conteneur-arrivage-delete-dialog.component';
import { AchatConteneurArrivageService } from 'app/entities/achat-conteneur-arrivage/achat-conteneur-arrivage.service';

describe('Component Tests', () => {
    describe('AchatConteneurArrivage Management Delete Component', () => {
        let comp: AchatConteneurArrivageDeleteDialogComponent;
        let fixture: ComponentFixture<AchatConteneurArrivageDeleteDialogComponent>;
        let service: AchatConteneurArrivageService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [AchatConteneurArrivageDeleteDialogComponent]
            })
                .overrideTemplate(AchatConteneurArrivageDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AchatConteneurArrivageDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AchatConteneurArrivageService);
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
