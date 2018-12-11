import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRecuperation } from 'app/shared/model/recuperation.model';
import { RecuperationService } from './recuperation.service';

@Component({
    selector: 'jhi-recuperation-delete-dialog',
    templateUrl: './recuperation-delete-dialog.component.html'
})
export class RecuperationDeleteDialogComponent {
    recuperation: IRecuperation;

    constructor(
        private recuperationService: RecuperationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.recuperationService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'recuperationListModification',
                content: 'Deleted an recuperation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-recuperation-delete-popup',
    template: ''
})
export class RecuperationDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ recuperation }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(RecuperationDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.recuperation = recuperation;
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
