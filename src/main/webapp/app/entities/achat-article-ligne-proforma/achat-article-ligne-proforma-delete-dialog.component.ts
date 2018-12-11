import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';
import { AchatArticleLigneProformaService } from './achat-article-ligne-proforma.service';

@Component({
    selector: 'jhi-achat-article-ligne-proforma-delete-dialog',
    templateUrl: './achat-article-ligne-proforma-delete-dialog.component.html'
})
export class AchatArticleLigneProformaDeleteDialogComponent {
    achatArticleLigneProforma: IAchatArticleLigneProforma;

    constructor(
        private achatArticleLigneProformaService: AchatArticleLigneProformaService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatArticleLigneProformaService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatArticleLigneProformaListModification',
                content: 'Deleted an achatArticleLigneProforma'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-article-ligne-proforma-delete-popup',
    template: ''
})
export class AchatArticleLigneProformaDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArticleLigneProforma }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatArticleLigneProformaDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatArticleLigneProforma = achatArticleLigneProforma;
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
