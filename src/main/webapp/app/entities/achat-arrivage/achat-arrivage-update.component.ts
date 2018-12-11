import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';
import { AchatArrivageService } from './achat-arrivage.service';
import { IAchatDossier } from 'app/shared/model/achat-dossier.model';
import { AchatDossierService } from 'app/entities/achat-dossier';

@Component({
    selector: 'jhi-achat-arrivage-update',
    templateUrl: './achat-arrivage-update.component.html'
})
export class AchatArrivageUpdateComponent implements OnInit {
    achatArrivage: IAchatArrivage;
    isSaving: boolean;

    achatdossiers: IAchatDossier[];
    dateArrivePortDp: any;
    dateRealisationDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatArrivageService: AchatArrivageService,
        private achatDossierService: AchatDossierService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatArrivage }) => {
            this.achatArrivage = achatArrivage;
        });
        this.achatDossierService.query().subscribe(
            (res: HttpResponse<IAchatDossier[]>) => {
                this.achatdossiers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatArrivage.id !== undefined) {
            this.subscribeToSaveResponse(this.achatArrivageService.update(this.achatArrivage));
        } else {
            this.subscribeToSaveResponse(this.achatArrivageService.create(this.achatArrivage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatArrivage>>) {
        result.subscribe((res: HttpResponse<IAchatArrivage>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAchatDossierById(index: number, item: IAchatDossier) {
        return item.id;
    }
}
