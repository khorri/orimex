import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';
import { AchatArticleConteneurReceptionService } from './achat-article-conteneur-reception.service';

@Component({
    selector: 'jhi-achat-article-conteneur-reception-delete-dialog',
    templateUrl: './achat-article-conteneur-reception-delete-dialog.component.html'
})
export class AchatArticleConteneurReceptionDeleteDialogComponent {
    achatArticleConteneurReception: IAchatArticleConteneurReception;

    constructor(
        private achatArticleConteneurReceptionService: AchatArticleConteneurReceptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatArticleConteneurReceptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatArticleConteneurReceptionListModification',
                content: 'Deleted an achatArticleConteneurReception'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-article-conteneur-reception-delete-popup',
    template: ''
})
export class AchatArticleConteneurReceptionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArticleConteneurReception }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatArticleConteneurReceptionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatArticleConteneurReception = achatArticleConteneurReception;
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
