import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';
import { AchatPrevisionArrivageService } from './achat-prevision-arrivage.service';

@Component({
    selector: 'jhi-achat-prevision-arrivage-delete-dialog',
    templateUrl: './achat-prevision-arrivage-delete-dialog.component.html'
})
export class AchatPrevisionArrivageDeleteDialogComponent {
    achatPrevisionArrivage: IAchatPrevisionArrivage;

    constructor(
        private achatPrevisionArrivageService: AchatPrevisionArrivageService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.achatPrevisionArrivageService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'achatPrevisionArrivageListModification',
                content: 'Deleted an achatPrevisionArrivage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-achat-prevision-arrivage-delete-popup',
    template: ''
})
export class AchatPrevisionArrivageDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatPrevisionArrivage }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AchatPrevisionArrivageDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.achatPrevisionArrivage = achatPrevisionArrivage;
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
