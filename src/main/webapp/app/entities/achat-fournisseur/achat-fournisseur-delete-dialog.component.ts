import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatFournisseur } from 'app/shared/model/achat-fournisseur.model';
import { AchatFournisseurService } from './achat-fournisseur.service';

@Component({
    selector: 'jhi-achat-fournisseur-delete-dialog',
    templateUrl: './achat-fournisseur-delete-dialog.component.html'
})
export class AchatFournisseurDeleteDialogComponent {
    achatFournisseur: IAchatFournisseur;

    constructor(
        private achatFournisseurService: AchatFournisseurService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatFournisseurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatFournisseurListModification',
                content: 'Deleted an achatFournisseur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-fournisseur-delete-popup',
    template: ''
})
export class AchatFournisseurDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatFournisseur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatFournisseurDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatFournisseur = achatFournisseur;
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
