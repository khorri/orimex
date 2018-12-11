import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';
import { AchatTypePaiementService } from './achat-type-paiement.service';

@Component({
    selector: 'jhi-achat-type-paiement-delete-dialog',
    templateUrl: './achat-type-paiement-delete-dialog.component.html'
})
export class AchatTypePaiementDeleteDialogComponent {
    achatTypePaiement: IAchatTypePaiement;

    constructor(
        private achatTypePaiementService: AchatTypePaiementService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatTypePaiementService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatTypePaiementListModification',
                content: 'Deleted an achatTypePaiement'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-type-paiement-delete-popup',
    template: ''
})
export class AchatTypePaiementDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatTypePaiement }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatTypePaiementDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatTypePaiement = achatTypePaiement;
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
