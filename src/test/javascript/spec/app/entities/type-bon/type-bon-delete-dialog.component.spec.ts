/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { TypeBonDeleteDialogComponent } from 'app/entities/type-bon/type-bon-delete-dialog.component';
import { TypeBonService } from 'app/entities/type-bon/type-bon.service';

describe('Component Tests', () => {
    describe('TypeBon Management Delete Component', () => {
        let comp: TypeBonDeleteDialogComponent;
        let fixture: ComponentFixture<TypeBonDeleteDialogComponent>;
        let service: TypeBonService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [TypeBonDeleteDialogComponent]
            })
                .overrideTemplate(TypeBonDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TypeBonDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TypeBonService);
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
