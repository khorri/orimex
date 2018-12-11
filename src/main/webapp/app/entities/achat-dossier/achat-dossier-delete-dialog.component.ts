import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatDossier } from 'app/shared/model/achat-dossier.model';
import { AchatDossierService } from './achat-dossier.service';

@Component({
    selector: 'jhi-achat-dossier-delete-dialog',
    templateUrl: './achat-dossier-delete-dialog.component.html'
})
export class AchatDossierDeleteDialogComponent {
    achatDossier: IAchatDossier;

    constructor(
        private achatDossierService: AchatDossierService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatDossierService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatDossierListModification',
                content: 'Deleted an achatDossier'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-dossier-delete-popup',
    template: ''
})
export class AchatDossierDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatDossier }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatDossierDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatDossier = achatDossier;
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
