import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';
import { AchatConteneurArrivageService } from './achat-conteneur-arrivage.service';

@Component({
    selector: 'jhi-achat-conteneur-arrivage-delete-dialog',
    templateUrl: './achat-conteneur-arrivage-delete-dialog.component.html'
})
export class AchatConteneurArrivageDeleteDialogComponent {
    achatConteneurArrivage: IAchatConteneurArrivage;

    constructor(
        private achatConteneurArrivageService: AchatConteneurArrivageService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatConteneurArrivageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatConteneurArrivageListModification',
                content: 'Deleted an achatConteneurArrivage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-conteneur-arrivage-delete-popup',
    template: ''
})
export class AchatConteneurArrivageDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatConteneurArrivage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatConteneurArrivageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatConteneurArrivage = achatConteneurArrivage;
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
