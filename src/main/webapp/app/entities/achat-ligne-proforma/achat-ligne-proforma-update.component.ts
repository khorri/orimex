import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';
import { AchatLigneProformaService } from './achat-ligne-proforma.service';
import { IAchatProforma } from 'app/shared/model/achat-proforma.model';
import { AchatProformaService } from 'app/entities/achat-proforma';

@Component({
    selector: 'jhi-achat-ligne-proforma-update',
    templateUrl: './achat-ligne-proforma-update.component.html'
})
export class AchatLigneProformaUpdateComponent implements OnInit {
    achatLigneProforma: IAchatLigneProforma;
    isSaving: boolean;

    achatproformas: IAchatProforma[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatLigneProformaService: AchatLigneProformaService,
        private achatProformaService: AchatProformaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatLigneProforma }) => {
            this.achatLigneProforma = achatLigneProforma;
        });
        this.achatProformaService.query().subscribe(
            (res: HttpResponse<IAchatProforma[]>) => {
                this.achatproformas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatLigneProforma.id !== undefined) {
            this.subscribeToSaveResponse(this.achatLigneProformaService.update(this.achatLigneProforma));
        } else {
            this.subscribeToSaveResponse(this.achatLigneProformaService.create(this.achatLigneProforma));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatLigneProforma>>) {
        result.subscribe((res: HttpResponse<IAchatLigneProforma>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackAchatProformaById(index: number, item: IAchatProforma) {
        return item.id;
    }
}
