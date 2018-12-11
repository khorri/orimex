import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICouleur } from 'app/shared/model/couleur.model';
import { CouleurService } from './couleur.service';

@Component({
    selector: 'jhi-couleur-update',
    templateUrl: './couleur-update.component.html'
})
export class CouleurUpdateComponent implements OnInit {
    couleur: ICouleur;
    isSaving: boolean;

    constructor(private couleurService: CouleurService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ couleur }) => {
            this.couleur = couleur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.couleur.id !== undefined) {
            this.subscribeToSaveResponse(this.couleurService.update(this.couleur));
        } else {
            this.subscribeToSaveResponse(this.couleurService.create(this.couleur));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICouleur>>) {
        result.subscribe((res: HttpResponse<ICouleur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
