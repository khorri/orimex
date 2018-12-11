import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';
import { AchatArticleConteneurArrivageService } from './achat-article-conteneur-arrivage.service';
import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';
import { AchatConteneurArrivageService } from 'app/entities/achat-conteneur-arrivage';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit';

@Component({
    selector: 'jhi-achat-article-conteneur-arrivage-update',
    templateUrl: './achat-article-conteneur-arrivage-update.component.html'
})
export class AchatArticleConteneurArrivageUpdateComponent implements OnInit {
    achatArticleConteneurArrivage: IAchatArticleConteneurArrivage;
    isSaving: boolean;

    achatconteneurarrivages: IAchatConteneurArrivage[];

    produits: IProduit[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatArticleConteneurArrivageService: AchatArticleConteneurArrivageService,
        private achatConteneurArrivageService: AchatConteneurArrivageService,
        private produitService: ProduitService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatArticleConteneurArrivage }) => {
            this.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
        });
        this.achatConteneurArrivageService.query().subscribe(
            (res: HttpResponse<IAchatConteneurArrivage[]>) => {
                this.achatconteneurarrivages = res.body;
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

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackAchatConteneurArrivageById(index: number, item: IAchatConteneurArrivage) {
        return item.id;
    }

    trackProduitById(index: number, item: IProduit) {
        return item.id;
    }
}
