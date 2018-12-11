import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStockReception } from 'app/shared/model/stock-reception.model';
import { StockReceptionService } from './stock-reception.service';

@Component({
    selector: 'jhi-stock-reception-delete-dialog',
    templateUrl: './stock-reception-delete-dialog.component.html'
})
export class StockReceptionDeleteDialogComponent {
    stockReception: IStockReception;

    constructor(
        private stockReceptionService: StockReceptionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.stockReceptionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'stockReceptionListModification',
                content: 'Deleted an stockReception'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-stock-reception-delete-popup',
    template: ''
})
export class StockReceptionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stockReception }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(StockReceptionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.stockReception = stockReception;
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
