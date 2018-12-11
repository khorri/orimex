import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';
import { StockConteneurReceptionService } from './stock-conteneur-reception.service';
import { IStockReception } from 'app/shared/model/stock-reception.model';
import { StockReceptionService } from 'app/entities/stock-reception';

@Component({
    selector: 'jhi-stock-conteneur-reception-update',
    templateUrl: './stock-conteneur-reception-update.component.html'
})
export class StockConteneurReceptionUpdateComponent implements OnInit {
    stockConteneurReception: IStockConteneurReception;
    isSaving: boolean;

    stockreceptions: IStockReception[];
    dateReceptionDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private stockConteneurReceptionService: StockConteneurReceptionService,
        private stockReceptionService: StockReceptionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ stockConteneurReception }) => {
            this.stockConteneurReception = stockConteneurReception;
        });
        this.stockReceptionService.query({ filter: 'stockconteneurreception-is-null' }).subscribe(
            (res: HttpResponse<IStockReception[]>) => {
                if (!this.stockConteneurReception.stockReceptionId) {
                    this.stockreceptions = res.body;
                } else {
                    this.stockReceptionService.find(this.stockConteneurReception.stockReceptionId).subscribe(
                        (subRes: HttpResponse<IStockReception>) => {
                            this.stockreceptions = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.stockConteneurReception.id !== undefined) {
            this.subscribeToSaveResponse(this.stockConteneurReceptionService.update(this.stockConteneurReception));
        } else {
            this.subscribeToSaveResponse(this.stockConteneurReceptionService.create(this.stockConteneurReception));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStockConteneurReception>>) {
        result.subscribe(
            (res: HttpResponse<IStockConteneurReception>) => this.onSaveSuccess(),
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

    trackStockReceptionById(index: number, item: IStockReception) {
        return item.id;
    }
}
