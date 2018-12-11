import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IStockReception } from 'app/shared/model/stock-reception.model';
import { StockReceptionService } from './stock-reception.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
    selector: 'jhi-stock-reception-update',
    templateUrl: './stock-reception-update.component.html'
})
export class StockReceptionUpdateComponent implements OnInit {
    stockReception: IStockReception;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];
    dateReceptionDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private stockReceptionService: StockReceptionService,
        private utilisateurService: UtilisateurService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ stockReception }) => {
            this.stockReception = stockReception;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.stockReception.id !== undefined) {
            this.subscribeToSaveResponse(this.stockReceptionService.update(this.stockReception));
        } else {
            this.subscribeToSaveResponse(this.stockReceptionService.create(this.stockReception));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStockReception>>) {
        result.subscribe((res: HttpResponse<IStockReception>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }
}
