import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConteneur } from 'app/shared/model/conteneur.model';
import { ConteneurService } from './conteneur.service';

@Component({
    selector: 'jhi-conteneur-delete-dialog',
    templateUrl: './conteneur-delete-dialog.component.html'
})
export class ConteneurDeleteDialogComponent {
    conteneur: IConteneur;

    constructor(private conteneurService: ConteneurService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.conteneurService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'conteneurListModification',
                content: 'Deleted an conteneur'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-conteneur-delete-popup',
    template: ''
})
export class ConteneurDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conteneur }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ConteneurDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.conteneur = conteneur;
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
