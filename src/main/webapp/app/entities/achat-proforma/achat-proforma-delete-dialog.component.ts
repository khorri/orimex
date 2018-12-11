import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatProforma } from 'app/shared/model/achat-proforma.model';
import { AchatProformaService } from './achat-proforma.service';

@Component({
    selector: 'jhi-achat-proforma-delete-dialog',
    templateUrl: './achat-proforma-delete-dialog.component.html'
})
export class AchatProformaDeleteDialogComponent {
    achatProforma: IAchatProforma;

    constructor(
        private achatProformaService: AchatProformaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatProformaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatProformaListModification',
                content: 'Deleted an achatProforma'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-proforma-delete-popup',
    template: ''
})
export class AchatProformaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatProforma }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatProformaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatProforma = achatProforma;
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
