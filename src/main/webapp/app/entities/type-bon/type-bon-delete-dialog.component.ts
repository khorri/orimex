import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITypeBon } from 'app/shared/model/type-bon.model';
import { TypeBonService } from './type-bon.service';

@Component({
    selector: 'jhi-type-bon-delete-dialog',
    templateUrl: './type-bon-delete-dialog.component.html'
})
export class TypeBonDeleteDialogComponent {
    typeBon: ITypeBon;

    constructor(private typeBonService: TypeBonService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.typeBonService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'typeBonListModification',
                content: 'Deleted an typeBon'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-type-bon-delete-popup',
    template: ''
})
export class TypeBonDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeBon }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TypeBonDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.typeBon = typeBon;
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
