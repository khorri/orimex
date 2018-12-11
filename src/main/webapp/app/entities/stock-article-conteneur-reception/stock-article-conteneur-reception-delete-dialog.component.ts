import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';
import { StockArticleConteneurReceptionService } from './stock-article-conteneur-reception.service';

@Component({
    selector: 'jhi-stock-article-conteneur-reception-delete-dialog',
    templateUrl: './stock-article-conteneur-reception-delete-dialog.component.html'
})
export class StockArticleConteneurReceptionDeleteDialogComponent {
    stockArticleConteneurReception: IStockArticleConteneurReception;

    constructor(
        private stockArticleConteneurReceptionService: StockArticleConteneurReceptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stockArticleConteneurReceptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'stockArticleConteneurReceptionListModification',
                content: 'Deleted an stockArticleConteneurReception'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-stock-article-conteneur-reception-delete-popup',
    template: ''
})
export class StockArticleConteneurReceptionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stockArticleConteneurReception }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StockArticleConteneurReceptionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.stockArticleConteneurReception = stockArticleConteneurReception;
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
