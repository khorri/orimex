/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { FamilleProduitDeleteDialogComponent } from 'app/entities/famille-produit/famille-produit-delete-dialog.component';
import { FamilleProduitService } from 'app/entities/famille-produit/famille-produit.service';

describe('Component Tests', () => {
    describe('FamilleProduit Management Delete Component', () => {
        let comp: FamilleProduitDeleteDialogComponent;
        let fixture: ComponentFixture<FamilleProduitDeleteDialogComponent>;
        let service: FamilleProduitService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [FamilleProduitDeleteDialogComponent]
            })
                .overrideTemplate(FamilleProduitDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FamilleProduitDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FamilleProduitService);
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
