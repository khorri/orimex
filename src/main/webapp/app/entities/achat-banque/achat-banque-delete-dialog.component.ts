import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatBanque } from 'app/shared/model/achat-banque.model';
import { AchatBanqueService } from './achat-banque.service';

@Component({
    selector: 'jhi-achat-banque-delete-dialog',
    templateUrl: './achat-banque-delete-dialog.component.html'
})
export class AchatBanqueDeleteDialogComponent {
    achatBanque: IAchatBanque;

    constructor(
        private achatBanqueService: AchatBanqueService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatBanqueService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatBanqueListModification',
                content: 'Deleted an achatBanque'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-banque-delete-popup',
    template: ''
})
export class AchatBanqueDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatBanque }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatBanqueDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatBanque = achatBanque;
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
