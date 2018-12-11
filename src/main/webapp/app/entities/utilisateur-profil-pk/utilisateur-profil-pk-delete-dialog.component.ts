import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';
import { UtilisateurProfilPKService } from './utilisateur-profil-pk.service';

@Component({
    selector: 'jhi-utilisateur-profil-pk-delete-dialog',
    templateUrl: './utilisateur-profil-pk-delete-dialog.component.html'
})
export class UtilisateurProfilPKDeleteDialogComponent {
    utilisateurProfilPK: IUtilisateurProfilPK;

    constructor(
        private utilisateurProfilPKService: UtilisateurProfilPKService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.utilisateurProfilPKService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'utilisateurProfilPKListModification',
                content: 'Deleted an utilisateurProfilPK'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-utilisateur-profil-pk-delete-popup',
    template: ''
})
export class UtilisateurProfilPKDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurProfilPK }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UtilisateurProfilPKDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.utilisateurProfilPK = utilisateurProfilPK;
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
