/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { OrimexTestModule } from '../../../test.module';
import { ConteneurDeleteDialogComponent } from 'app/entities/conteneur/conteneur-delete-dialog.component';
import { ConteneurService } from 'app/entities/conteneur/conteneur.service';

describe('Component Tests', () => {
    describe('Conteneur Management Delete Component', () => {
        let comp: ConteneurDeleteDialogComponent;
        let fixture: ComponentFixture<ConteneurDeleteDialogComponent>;
        let service: ConteneurService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [OrimexTestModule],
                declarations: [ConteneurDeleteDialogComponent]
            })
                .overrideTemplate(ConteneurDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ConteneurDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConteneurService);
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
