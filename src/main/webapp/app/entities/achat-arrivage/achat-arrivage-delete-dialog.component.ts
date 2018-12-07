import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';
import { AchatArrivageService } from './achat-arrivage.service';

@Component({
    selector: 'jhi-achat-arrivage-delete-dialog',
    templateUrl: './achat-arrivage-delete-dialog.component.html'
})
export class AchatArrivageDeleteDialogComponent {
    achatArrivage: IAchatArrivage;

    constructor(
        private achatArrivageService: AchatArrivageService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatArrivageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatArrivageListModification',
                content: 'Deleted an achatArrivage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-arrivage-delete-popup',
    template: ''
})
export class AchatArrivageDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArrivage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatArrivageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatArrivage = achatArrivage;
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
