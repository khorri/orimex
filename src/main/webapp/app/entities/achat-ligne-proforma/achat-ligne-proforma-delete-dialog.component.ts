import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';
import { AchatLigneProformaService } from './achat-ligne-proforma.service';

@Component({
    selector: 'jhi-achat-ligne-proforma-delete-dialog',
    templateUrl: './achat-ligne-proforma-delete-dialog.component.html'
})
export class AchatLigneProformaDeleteDialogComponent {
    achatLigneProforma: IAchatLigneProforma;

    constructor(
        private achatLigneProformaService: AchatLigneProformaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatLigneProformaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatLigneProformaListModification',
                content: 'Deleted an achatLigneProforma'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-ligne-proforma-delete-popup',
    template: ''
})
export class AchatLigneProformaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatLigneProforma }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatLigneProformaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatLigneProforma = achatLigneProforma;
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
