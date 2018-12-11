import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFamilleProduit } from 'app/shared/model/famille-produit.model';
import { FamilleProduitService } from './famille-produit.service';

@Component({
    selector: 'jhi-famille-produit-delete-dialog',
    templateUrl: './famille-produit-delete-dialog.component.html'
})
export class FamilleProduitDeleteDialogComponent {
    familleProduit: IFamilleProduit;

    constructor(
        private familleProduitService: FamilleProduitService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.familleProduitService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'familleProduitListModification',
                content: 'Deleted an familleProduit'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-famille-produit-delete-popup',
    template: ''
})
export class FamilleProduitDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ familleProduit }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FamilleProduitDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.familleProduit = familleProduit;
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
