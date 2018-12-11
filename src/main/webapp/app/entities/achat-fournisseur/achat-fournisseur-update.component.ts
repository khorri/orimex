import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatFournisseur } from 'app/shared/model/achat-fournisseur.model';
import { AchatFournisseurService } from './achat-fournisseur.service';

@Component({
    selector: 'jhi-achat-fournisseur-update',
    templateUrl: './achat-fournisseur-update.component.html'
})
export class AchatFournisseurUpdateComponent implements OnInit {
    achatFournisseur: IAchatFournisseur;
    isSaving: boolean;

    constructor(private achatFournisseurService: AchatFournisseurService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatFournisseur }) => {
            this.achatFournisseur = achatFournisseur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatFournisseur.id !== undefined) {
            this.subscribeToSaveResponse(this.achatFournisseurService.update(this.achatFournisseur));
        } else {
            this.subscribeToSaveResponse(this.achatFournisseurService.create(this.achatFournisseur));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatFournisseur>>) {
        result.subscribe((res: HttpResponse<IAchatFournisseur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
