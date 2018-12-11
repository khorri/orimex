import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IConteneur } from 'app/shared/model/conteneur.model';
import { ConteneurService } from './conteneur.service';

@Component({
    selector: 'jhi-conteneur-update',
    templateUrl: './conteneur-update.component.html'
})
export class ConteneurUpdateComponent implements OnInit {
    conteneur: IConteneur;
    isSaving: boolean;

    constructor(private conteneurService: ConteneurService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ conteneur }) => {
            this.conteneur = conteneur;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.conteneur.id !== undefined) {
            this.subscribeToSaveResponse(this.conteneurService.update(this.conteneur));
        } else {
            this.subscribeToSaveResponse(this.conteneurService.create(this.conteneur));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IConteneur>>) {
        result.subscribe((res: HttpResponse<IConteneur>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
