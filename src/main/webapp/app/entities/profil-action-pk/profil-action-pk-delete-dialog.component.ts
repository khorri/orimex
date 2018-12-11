import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilActionPK } from 'app/shared/model/profil-action-pk.model';
import { ProfilActionPKService } from './profil-action-pk.service';

@Component({
    selector: 'jhi-profil-action-pk-delete-dialog',
    templateUrl: './profil-action-pk-delete-dialog.component.html'
})
export class ProfilActionPKDeleteDialogComponent {
    profilActionPK: IProfilActionPK;

    constructor(
        private profilActionPKService: ProfilActionPKService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilActionPKService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'profilActionPKListModification',
                content: 'Deleted an profilActionPK'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profil-action-pk-delete-popup',
    template: ''
})
export class ProfilActionPKDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilActionPK }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProfilActionPKDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.profilActionPK = profilActionPK;
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
