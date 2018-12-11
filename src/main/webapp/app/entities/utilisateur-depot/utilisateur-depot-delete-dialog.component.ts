import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';
import { UtilisateurDepotService } from './utilisateur-depot.service';

@Component({
    selector: 'jhi-utilisateur-depot-delete-dialog',
    templateUrl: './utilisateur-depot-delete-dialog.component.html'
})
export class UtilisateurDepotDeleteDialogComponent {
    utilisateurDepot: IUtilisateurDepot;

    constructor(
        private utilisateurDepotService: UtilisateurDepotService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.utilisateurDepotService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'utilisateurDepotListModification',
                content: 'Deleted an utilisateurDepot'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-utilisateur-depot-delete-popup',
    template: ''
})
export class UtilisateurDepotDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurDepot }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UtilisateurDepotDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.utilisateurDepot = utilisateurDepot;
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
