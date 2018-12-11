import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';
import { StockConteneurReceptionService } from './stock-conteneur-reception.service';

@Component({
    selector: 'jhi-stock-conteneur-reception-delete-dialog',
    templateUrl: './stock-conteneur-reception-delete-dialog.component.html'
})
export class StockConteneurReceptionDeleteDialogComponent {
    stockConteneurReception: IStockConteneurReception;

    constructor(
        private stockConteneurReceptionService: StockConteneurReceptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stockConteneurReceptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'stockConteneurReceptionListModification',
                content: 'Deleted an stockConteneurReception'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-stock-conteneur-reception-delete-popup',
    template: ''
})
export class StockConteneurReceptionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stockConteneurReception }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StockConteneurReceptionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.stockConteneurReception = stockConteneurReception;
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
