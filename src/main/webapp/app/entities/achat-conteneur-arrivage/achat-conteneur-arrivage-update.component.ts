import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';
import { AchatConteneurArrivageService } from './achat-conteneur-arrivage.service';
import { IAchatFacture } from 'app/shared/model/achat-facture.model';
import { AchatFactureService } from 'app/entities/achat-facture';
import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';
import { StockConteneurReceptionService } from 'app/entities/stock-conteneur-reception';

@Component({
    selector: 'jhi-achat-conteneur-arrivage-update',
    templateUrl: './achat-conteneur-arrivage-update.component.html'
})
export class AchatConteneurArrivageUpdateComponent implements OnInit {
    achatConteneurArrivage: IAchatConteneurArrivage;
    isSaving: boolean;

    achatfactures: IAchatFacture[];

    stockconteneurreceptions: IStockConteneurReception[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatConteneurArrivageService: AchatConteneurArrivageService,
        private achatFactureService: AchatFactureService,
        private stockConteneurReceptionService: StockConteneurReceptionService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatConteneurArrivage }) => {
            this.achatConteneurArrivage = achatConteneurArrivage;
        });
        this.achatFactureService.query().subscribe(
            (res: HttpResponse<IAchatFacture[]>) => {
                this.achatfactures = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.stockConteneurReceptionService.query({ filter: 'achatconteneurarrivage-is-null' }).subscribe(
            (res: HttpResponse<IStockConteneurReception[]>) => {
                if (!this.achatConteneurArrivage.stockConteneurReceptionId) {
                    this.stockconteneurreceptions = res.body;
                } else {
                    this.stockConteneurReceptionService.find(this.achatConteneurArrivage.stockConteneurReceptionId).subscribe(
                        (subRes: HttpResponse<IStockConteneurReception>) => {
                            this.stockconteneurreceptions = [subRes.body].concat(res.body);
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
        if (this.achatConteneurArrivage.id !== undefined) {
            this.subscribeToSaveResponse(this.achatConteneurArrivageService.update(this.achatConteneurArrivage));
        } else {
            this.subscribeToSaveResponse(this.achatConteneurArrivageService.create(this.achatConteneurArrivage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatConteneurArrivage>>) {
        result.subscribe(
            (res: HttpResponse<IAchatConteneurArrivage>) => this.onSaveSuccess(),
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

    trackAchatFactureById(index: number, item: IAchatFacture) {
        return item.id;
    }

    trackStockConteneurReceptionById(index: number, item: IStockConteneurReception) {
        return item.id;
    }
}
