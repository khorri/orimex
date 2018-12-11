import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';
import { AchatStatutDossierService } from './achat-statut-dossier.service';

@Component({
    selector: 'jhi-achat-statut-dossier-update',
    templateUrl: './achat-statut-dossier-update.component.html'
})
export class AchatStatutDossierUpdateComponent implements OnInit {
    achatStatutDossier: IAchatStatutDossier;
    isSaving: boolean;

    constructor(private achatStatutDossierService: AchatStatutDossierService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatStatutDossier }) => {
            this.achatStatutDossier = achatStatutDossier;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatStatutDossier.id !== undefined) {
            this.subscribeToSaveResponse(this.achatStatutDossierService.update(this.achatStatutDossier));
        } else {
            this.subscribeToSaveResponse(this.achatStatutDossierService.create(this.achatStatutDossier));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatStatutDossier>>) {
        result.subscribe((res: HttpResponse<IAchatStatutDossier>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
