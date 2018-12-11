import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';
import { AchatConteneurReceptionService } from './achat-conteneur-reception.service';

@Component({
    selector: 'jhi-achat-conteneur-reception-delete-dialog',
    templateUrl: './achat-conteneur-reception-delete-dialog.component.html'
})
export class AchatConteneurReceptionDeleteDialogComponent {
    achatConteneurReception: IAchatConteneurReception;

    constructor(
        private achatConteneurReceptionService: AchatConteneurReceptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatConteneurReceptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatConteneurReceptionListModification',
                content: 'Deleted an achatConteneurReception'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-conteneur-reception-delete-popup',
    template: ''
})
export class AchatConteneurReceptionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatConteneurReception }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatConteneurReceptionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatConteneurReception = achatConteneurReception;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
