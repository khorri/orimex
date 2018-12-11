/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { RecuperationDeleteDialogComponent } from 'app/entities/recuperation/recuperation-delete-dialog.component';
import { RecuperationService } from 'app/entities/recuperation/recuperation.service';

describe('Component Tests', () => {
    describe('Recuperation Management Delete Component', () => {
        let comp: RecuperationDeleteDialogComponent;
        let fixture: ComponentFixture<RecuperationDeleteDialogComponent>;
        let service: RecuperationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [RecuperationDeleteDialogComponent]
            })
                .overrideTemplate(RecuperationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(RecuperationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RecuperationService);
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
