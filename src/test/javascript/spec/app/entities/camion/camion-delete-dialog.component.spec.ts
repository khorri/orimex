/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { CamionDeleteDialogComponent } from 'app/entities/camion/camion-delete-dialog.component';
import { CamionService } from 'app/entities/camion/camion.service';

describe('Component Tests', () => {
    describe('Camion Management Delete Component', () => {
        let comp: CamionDeleteDialogComponent;
        let fixture: ComponentFixture<CamionDeleteDialogComponent>;
        let service: CamionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [CamionDeleteDialogComponent]
            })
                .overrideTemplate(CamionDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CamionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CamionService);
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
