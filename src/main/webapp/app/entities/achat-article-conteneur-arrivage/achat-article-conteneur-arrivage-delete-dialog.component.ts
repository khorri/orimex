import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';
import { AchatArticleConteneurArrivageService } from './achat-article-conteneur-arrivage.service';

@Component({
    selector: 'jhi-achat-article-conteneur-arrivage-delete-dialog',
    templateUrl: './achat-article-conteneur-arrivage-delete-dialog.component.html'
})
export class AchatArticleConteneurArrivageDeleteDialogComponent {
    achatArticleConteneurArrivage: IAchatArticleConteneurArrivage;

    constructor(
        private achatArticleConteneurArrivageService: AchatArticleConteneurArrivageService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatArticleConteneurArrivageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatArticleConteneurArrivageListModification',
                content: 'Deleted an achatArticleConteneurArrivage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-article-conteneur-arrivage-delete-popup',
    template: ''
})
export class AchatArticleConteneurArrivageDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArticleConteneurArrivage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatArticleConteneurArrivageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
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
