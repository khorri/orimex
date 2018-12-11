import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatDevise } from 'app/shared/model/achat-devise.model';
import { AchatDeviseService } from './achat-devise.service';

@Component({
    selector: 'jhi-achat-devise-delete-dialog',
    templateUrl: './achat-devise-delete-dialog.component.html'
})
export class AchatDeviseDeleteDialogComponent {
    achatDevise: IAchatDevise;

    constructor(
        private achatDeviseService: AchatDeviseService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatDeviseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatDeviseListModification',
                content: 'Deleted an achatDevise'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-devise-delete-popup',
    template: ''
})
export class AchatDeviseDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatDevise }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatDeviseDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatDevise = achatDevise;
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
