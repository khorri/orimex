/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { JourFerierDeleteDialogComponent } from 'app/entities/jour-ferier/jour-ferier-delete-dialog.component';
import { JourFerierService } from 'app/entities/jour-ferier/jour-ferier.service';

describe('Component Tests', () => {
    describe('JourFerier Management Delete Component', () => {
        let comp: JourFerierDeleteDialogComponent;
        let fixture: ComponentFixture<JourFerierDeleteDialogComponent>;
        let service: JourFerierService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [JourFerierDeleteDialogComponent]
            })
                .overrideTemplate(JourFerierDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(JourFerierDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(JourFerierService);
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
