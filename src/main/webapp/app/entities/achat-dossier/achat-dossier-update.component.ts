import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatDossier } from 'app/shared/model/achat-dossier.model';
import { AchatDossierService } from './achat-dossier.service';
import { IAchatBanque } from 'app/shared/model/achat-banque.model';
import { AchatBanqueService } from 'app/entities/achat-banque';
import { IAchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';
import { AchatTypePaiementService } from 'app/entities/achat-type-paiement';
import { IAchatDevise } from 'app/shared/model/achat-devise.model';
import { AchatDeviseService } from 'app/entities/achat-devise';
import { IAchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';
import { AchatStatutDossierService } from 'app/entities/achat-statut-dossier';

@Component({
    selector: 'jhi-achat-dossier-update',
    templateUrl: './achat-dossier-update.component.html'
})
export class AchatDossierUpdateComponent implements OnInit {
    achatDossier: IAchatDossier;
    isSaving: boolean;

    achatbanques: IAchatBanque[];

    achattypepaiements: IAchatTypePaiement[];

    achatdevises: IAchatDevise[];

    achatstatutdossiers: IAchatStatutDossier[];
    dateCreationDp: any;
    dateExpeditionDp: any;
    dateOuvertureDp: any;
    dateValiditeEiDp: any;
    dateLimiteExpDp: any;
    dateValiditeLcDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatDossierService: AchatDossierService,
        private achatBanqueService: AchatBanqueService,
        private achatTypePaiementService: AchatTypePaiementService,
        private achatDeviseService: AchatDeviseService,
        private achatStatutDossierService: AchatStatutDossierService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatDossier }) => {
            this.achatDossier = achatDossier;
        });
        this.achatBanqueService.query().subscribe(
            (res: HttpResponse<IAchatBanque[]>) => {
                this.achatbanques = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.achatTypePaiementService.query().subscribe(
            (res: HttpResponse<IAchatTypePaiement[]>) => {
                this.achattypepaiements = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.achatDeviseService.query().subscribe(
            (res: HttpResponse<IAchatDevise[]>) => {
                this.achatdevises = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.achatStatutDossierService.query().subscribe(
            (res: HttpResponse<IAchatStatutDossier[]>) => {
                this.achatstatutdossiers = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatDossier.id !== undefined) {
            this.subscribeToSaveResponse(this.achatDossierService.update(this.achatDossier));
        } else {
            this.subscribeToSaveResponse(this.achatDossierService.create(this.achatDossier));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatDossier>>) {
        result.subscribe((res: HttpResponse<IAchatDossier>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAchatBanqueById(index: number, item: IAchatBanque) {
        return item.id;
    }

    trackAchatTypePaiementById(index: number, item: IAchatTypePaiement) {
        return item.id;
    }

    trackAchatDeviseById(index: number, item: IAchatDevise) {
        return item.id;
    }

    trackAchatStatutDossierById(index: number, item: IAchatStatutDossier) {
        return item.id;
    }
}
