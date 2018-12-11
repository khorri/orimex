import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';
import { UtilisateurProfilService } from './utilisateur-profil.service';

@Component({
    selector: 'jhi-utilisateur-profil-delete-dialog',
    templateUrl: './utilisateur-profil-delete-dialog.component.html'
})
export class UtilisateurProfilDeleteDialogComponent {
    utilisateurProfil: IUtilisateurProfil;

    constructor(
        private utilisateurProfilService: UtilisateurProfilService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.utilisateurProfilService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'utilisateurProfilListModification',
                content: 'Deleted an utilisateurProfil'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-utilisateur-profil-delete-popup',
    template: ''
})
export class UtilisateurProfilDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurProfil }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UtilisateurProfilDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.utilisateurProfil = utilisateurProfil;
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
