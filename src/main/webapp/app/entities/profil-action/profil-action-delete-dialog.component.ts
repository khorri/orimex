import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProfilAction } from 'app/shared/model/profil-action.model';
import { ProfilActionService } from './profil-action.service';

@Component({
    selector: 'jhi-profil-action-delete-dialog',
    templateUrl: './profil-action-delete-dialog.component.html'
})
export class ProfilActionDeleteDialogComponent {
    profilAction: IProfilAction;

    constructor(
        private profilActionService: ProfilActionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.profilActionService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'profilActionListModification',
                content: 'Deleted an profilAction'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-profil-action-delete-popup',
    template: ''
})
export class ProfilActionDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilAction }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ProfilActionDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.profilAction = profilAction;
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
