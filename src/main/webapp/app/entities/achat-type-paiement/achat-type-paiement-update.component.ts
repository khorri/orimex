import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';
import { AchatTypePaiementService } from './achat-type-paiement.service';

@Component({
    selector: 'jhi-achat-type-paiement-update',
    templateUrl: './achat-type-paiement-update.component.html'
})
export class AchatTypePaiementUpdateComponent implements OnInit {
    achatTypePaiement: IAchatTypePaiement;
    isSaving: boolean;

    constructor(private achatTypePaiementService: AchatTypePaiementService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatTypePaiement }) => {
            this.achatTypePaiement = achatTypePaiement;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatTypePaiement.id !== undefined) {
            this.subscribeToSaveResponse(this.achatTypePaiementService.update(this.achatTypePaiement));
        } else {
            this.subscribeToSaveResponse(this.achatTypePaiementService.create(this.achatTypePaiement));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatTypePaiement>>) {
        result.subscribe((res: HttpResponse<IAchatTypePaiement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
