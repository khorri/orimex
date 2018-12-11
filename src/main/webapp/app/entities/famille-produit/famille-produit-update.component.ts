import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IFamilleProduit } from 'app/shared/model/famille-produit.model';
import { FamilleProduitService } from './famille-produit.service';

@Component({
    selector: 'jhi-famille-produit-update',
    templateUrl: './famille-produit-update.component.html'
})
export class FamilleProduitUpdateComponent implements OnInit {
    familleProduit: IFamilleProduit;
    isSaving: boolean;

    constructor(private familleProduitService: FamilleProduitService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ familleProduit }) => {
            this.familleProduit = familleProduit;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.familleProduit.id !== undefined) {
            this.subscribeToSaveResponse(this.familleProduitService.update(this.familleProduit));
        } else {
            this.subscribeToSaveResponse(this.familleProduitService.create(this.familleProduit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IFamilleProduit>>) {
        result.subscribe((res: HttpResponse<IFamilleProduit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
