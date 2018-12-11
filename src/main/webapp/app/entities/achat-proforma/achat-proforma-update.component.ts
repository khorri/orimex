import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatProforma } from 'app/shared/model/achat-proforma.model';
import { AchatProformaService } from './achat-proforma.service';
import { IAchatDossier } from 'app/shared/model/achat-dossier.model';
import { AchatDossierService } from 'app/entities/achat-dossier';

@Component({
    selector: 'jhi-achat-proforma-update',
    templateUrl: './achat-proforma-update.component.html'
})
export class AchatProformaUpdateComponent implements OnInit {
    achatProforma: IAchatProforma;
    isSaving: boolean;

    achatdossiers: IAchatDossier[];
    dateProformaDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatProformaService: AchatProformaService,
        private achatDossierService: AchatDossierService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatProforma }) => {
            this.achatProforma = achatProforma;
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
        if (this.achatProforma.id !== undefined) {
            this.subscribeToSaveResponse(this.achatProformaService.update(this.achatProforma));
        } else {
            this.subscribeToSaveResponse(this.achatProformaService.create(this.achatProforma));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatProforma>>) {
        result.subscribe((res: HttpResponse<IAchatProforma>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
