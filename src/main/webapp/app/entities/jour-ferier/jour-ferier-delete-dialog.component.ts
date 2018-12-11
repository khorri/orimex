import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IJourFerier } from 'app/shared/model/jour-ferier.model';
import { JourFerierService } from './jour-ferier.service';

@Component({
    selector: 'jhi-jour-ferier-delete-dialog',
    templateUrl: './jour-ferier-delete-dialog.component.html'
})
export class JourFerierDeleteDialogComponent {
    jourFerier: IJourFerier;

    constructor(private jourFerierService: JourFerierService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.jourFerierService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'jourFerierListModification',
                content: 'Deleted an jourFerier'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-jour-ferier-delete-popup',
    template: ''
})
export class JourFerierDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jourFerier }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(JourFerierDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.jourFerier = jourFerier;
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
