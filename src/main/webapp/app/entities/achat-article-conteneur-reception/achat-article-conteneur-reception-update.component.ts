import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';
import { AchatArticleConteneurReceptionService } from './achat-article-conteneur-reception.service';
import { IAchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';
import { AchatConteneurReceptionService } from 'app/entities/achat-conteneur-reception';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit';

@Component({
    selector: 'jhi-achat-article-conteneur-reception-update',
    templateUrl: './achat-article-conteneur-reception-update.component.html'
})
export class AchatArticleConteneurReceptionUpdateComponent implements OnInit {
    achatArticleConteneurReception: IAchatArticleConteneurReception;
    isSaving: boolean;

    achatconteneurreceptions: IAchatConteneurReception[];

    produits: IProduit[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatArticleConteneurReceptionService: AchatArticleConteneurReceptionService,
        private achatConteneurReceptionService: AchatConteneurReceptionService,
        private produitService: ProduitService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatArticleConteneurReception }) => {
            this.achatArticleConteneurReception = achatArticleConteneurReception;
        });
        this.achatConteneurReceptionService.query().subscribe(
            (res: HttpResponse<IAchatConteneurReception[]>) => {
                this.achatconteneurreceptions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.produitService.query().subscribe(
            (res: HttpResponse<IProduit[]>) => {
                this.produits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatArticleConteneurReception.id !== undefined) {
            this.subscribeToSaveResponse(this.achatArticleConteneurReceptionService.update(this.achatArticleConteneurReception));
        } else {
            this.subscribeToSaveResponse(this.achatArticleConteneurReceptionService.create(this.achatArticleConteneurReception));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatArticleConteneurReception>>) {
        result.subscribe(
            (res: HttpResponse<IAchatArticleConteneurReception>) => this.onSaveSuccess(),
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAchatConteneurReceptionById(index: number, item: IAchatConteneurReception) {
        return item.id;
    }

    trackProduitById(index: number, item: IProduit) {
        return item.id;
    }
}
