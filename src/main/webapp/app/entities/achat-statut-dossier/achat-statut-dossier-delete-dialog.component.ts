import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';
import { AchatStatutDossierService } from './achat-statut-dossier.service';

@Component({
    selector: 'jhi-achat-statut-dossier-delete-dialog',
    templateUrl: './achat-statut-dossier-delete-dialog.component.html'
})
export class AchatStatutDossierDeleteDialogComponent {
    achatStatutDossier: IAchatStatutDossier;

    constructor(
        private achatStatutDossierService: AchatStatutDossierService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatStatutDossierService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatStatutDossierListModification',
                content: 'Deleted an achatStatutDossier'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-statut-dossier-delete-popup',
    template: ''
})
export class AchatStatutDossierDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatStatutDossier }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatStatutDossierDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatStatutDossier = achatStatutDossier;
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
