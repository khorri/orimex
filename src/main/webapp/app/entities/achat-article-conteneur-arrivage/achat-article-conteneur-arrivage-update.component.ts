import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';
import { AchatArticleConteneurArrivageService } from './achat-article-conteneur-arrivage.service';

@Component({
    selector: 'jhi-achat-article-conteneur-arrivage-update',
    templateUrl: './achat-article-conteneur-arrivage-update.component.html'
})
export class AchatArticleConteneurArrivageUpdateComponent implements OnInit {
    achatArticleConteneurArrivage: IAchatArticleConteneurArrivage;
    isSaving: boolean;

    constructor(
        private achatArticleConteneurArrivageService: AchatArticleConteneurArrivageService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatArticleConteneurArrivage }) => {
            this.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatArticleConteneurArrivage.id !== undefined) {
            this.subscribeToSaveResponse(this.achatArticleConteneurArrivageService.update(this.achatArticleConteneurArrivage));
        } else {
            this.subscribeToSaveResponse(this.achatArticleConteneurArrivageService.create(this.achatArticleConteneurArrivage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatArticleConteneurArrivage>>) {
        result.subscribe(
            (res: HttpResponse<IAchatArticleConteneurArrivage>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
