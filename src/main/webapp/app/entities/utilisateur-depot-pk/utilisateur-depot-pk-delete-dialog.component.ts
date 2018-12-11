import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';
import { UtilisateurDepotPKService } from './utilisateur-depot-pk.service';

@Component({
    selector: 'jhi-utilisateur-depot-pk-delete-dialog',
    templateUrl: './utilisateur-depot-pk-delete-dialog.component.html'
})
export class UtilisateurDepotPKDeleteDialogComponent {
    utilisateurDepotPK: IUtilisateurDepotPK;

    constructor(
        private utilisateurDepotPKService: UtilisateurDepotPKService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.utilisateurDepotPKService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'utilisateurDepotPKListModification',
                content: 'Deleted an utilisateurDepotPK'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-utilisateur-depot-pk-delete-popup',
    template: ''
})
export class UtilisateurDepotPKDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurDepotPK }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UtilisateurDepotPKDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.utilisateurDepotPK = utilisateurDepotPK;
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
