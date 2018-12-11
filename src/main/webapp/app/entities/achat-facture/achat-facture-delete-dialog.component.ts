import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatFacture } from 'app/shared/model/achat-facture.model';
import { AchatFactureService } from './achat-facture.service';

@Component({
    selector: 'jhi-achat-facture-delete-dialog',
    templateUrl: './achat-facture-delete-dialog.component.html'
})
export class AchatFactureDeleteDialogComponent {
    achatFacture: IAchatFacture;

    constructor(
        private achatFactureService: AchatFactureService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatFactureService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatFactureListModification',
                content: 'Deleted an achatFacture'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-facture-delete-popup',
    template: ''
})
export class AchatFactureDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatFacture }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatFactureDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatFacture = achatFacture;
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
