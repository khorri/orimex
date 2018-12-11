import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IStockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';
import { StockArticleConteneurReceptionService } from './stock-article-conteneur-reception.service';
import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';
import { AchatArticleConteneurArrivageService } from 'app/entities/achat-article-conteneur-arrivage';
import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';
import { StockConteneurReceptionService } from 'app/entities/stock-conteneur-reception';

@Component({
    selector: 'jhi-stock-article-conteneur-reception-update',
    templateUrl: './stock-article-conteneur-reception-update.component.html'
})
export class StockArticleConteneurReceptionUpdateComponent implements OnInit {
    stockArticleConteneurReception: IStockArticleConteneurReception;
    isSaving: boolean;

    achatarticleconteneurarrivages: IAchatArticleConteneurArrivage[];

    stockconteneurreceptions: IStockConteneurReception[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private stockArticleConteneurReceptionService: StockArticleConteneurReceptionService,
        private achatArticleConteneurArrivageService: AchatArticleConteneurArrivageService,
        private stockConteneurReceptionService: StockConteneurReceptionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ stockArticleConteneurReception }) => {
            this.stockArticleConteneurReception = stockArticleConteneurReception;
        });
        this.achatArticleConteneurArrivageService.query({ filter: 'stockarticleconteneurreception-is-null' }).subscribe(
            (res: HttpResponse<IAchatArticleConteneurArrivage[]>) => {
                if (!this.stockArticleConteneurReception.achatArticleConteneurArrivageId) {
                    this.achatarticleconteneurarrivages = res.body;
                } else {
                    this.achatArticleConteneurArrivageService
                        .find(this.stockArticleConteneurReception.achatArticleConteneurArrivageId)
                        .subscribe(
                            (subRes: HttpResponse<IAchatArticleConteneurArrivage>) => {
                                this.achatarticleconteneurarrivages = [subRes.body].concat(res.body);
                            },
                            (subRes: HttpErrorResponse) => this.onError(subRes.message)
                        );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.stockConteneurReceptionService.query().subscribe(
            (res: HttpResponse<IStockConteneurReception[]>) => {
                this.stockconteneurreceptions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.stockArticleConteneurReception.id !== undefined) {
            this.subscribeToSaveResponse(this.stockArticleConteneurReceptionService.update(this.stockArticleConteneurReception));
        } else {
            this.subscribeToSaveResponse(this.stockArticleConteneurReceptionService.create(this.stockArticleConteneurReception));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStockArticleConteneurReception>>) {
        result.subscribe(
            (res: HttpResponse<IStockArticleConteneurReception>) => this.onSaveSuccess(),
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

    trackAchatArticleConteneurArrivageById(index: number, item: IAchatArticleConteneurArrivage) {
        return item.id;
    }

    trackStockConteneurReceptionById(index: number, item: IStockConteneurReception) {
        return item.id;
    }
}
