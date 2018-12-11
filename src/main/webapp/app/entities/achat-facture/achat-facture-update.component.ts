import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatFacture } from 'app/shared/model/achat-facture.model';
import { AchatFactureService } from './achat-facture.service';
import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';
import { AchatArrivageService } from 'app/entities/achat-arrivage';

@Component({
    selector: 'jhi-achat-facture-update',
    templateUrl: './achat-facture-update.component.html'
})
export class AchatFactureUpdateComponent implements OnInit {
    achatFacture: IAchatFacture;
    isSaving: boolean;

    achatarrivages: IAchatArrivage[];
    dateFactureDp: any;
    dateBlDp: any;
    dateEcheanceDp: any;
    dateValeurDp: any;
    echecanceFinancementDp: any;
    dateReglementDp: any;
    derniereEcheanceDp: any;
    echeanceRefinancementDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatFactureService: AchatFactureService,
        private achatArrivageService: AchatArrivageService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatFacture }) => {
            this.achatFacture = achatFacture;
        });
        this.achatArrivageService.query().subscribe(
            (res: HttpResponse<IAchatArrivage[]>) => {
                this.achatarrivages = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatFacture.id !== undefined) {
            this.subscribeToSaveResponse(this.achatFactureService.update(this.achatFacture));
        } else {
            this.subscribeToSaveResponse(this.achatFactureService.create(this.achatFacture));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatFacture>>) {
        result.subscribe((res: HttpResponse<IAchatFacture>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAchatArrivageById(index: number, item: IAchatArrivage) {
        return item.id;
    }
}
