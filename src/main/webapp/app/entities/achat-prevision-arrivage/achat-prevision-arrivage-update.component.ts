import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';
import { AchatPrevisionArrivageService } from './achat-prevision-arrivage.service';
import { IAchatDossier } from 'app/shared/model/achat-dossier.model';
import { AchatDossierService } from 'app/entities/achat-dossier';

@Component({
    selector: 'jhi-achat-prevision-arrivage-update',
    templateUrl: './achat-prevision-arrivage-update.component.html'
})
export class AchatPrevisionArrivageUpdateComponent implements OnInit {
    achatPrevisionArrivage: IAchatPrevisionArrivage;
    isSaving: boolean;

    achatdossiers: IAchatDossier[];
    etdDp: any;
    etaDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatPrevisionArrivageService: AchatPrevisionArrivageService,
        private achatDossierService: AchatDossierService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatPrevisionArrivage }) => {
            this.achatPrevisionArrivage = achatPrevisionArrivage;
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
        if (this.achatPrevisionArrivage.id !== undefined) {
            this.subscribeToSaveResponse(this.achatPrevisionArrivageService.update(this.achatPrevisionArrivage));
        } else {
            this.subscribeToSaveResponse(this.achatPrevisionArrivageService.create(this.achatPrevisionArrivage));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatPrevisionArrivage>>) {
        result.subscribe(
            (res: HttpResponse<IAchatPrevisionArrivage>) => this.onSaveSuccess(),
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

    trackAchatDossierById(index: number, item: IAchatDossier) {
        return item.id;
    }
}
