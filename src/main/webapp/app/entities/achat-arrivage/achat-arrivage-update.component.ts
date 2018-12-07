import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';
import { AchatArrivageService } from './achat-arrivage.service';

@Component({
    selector: 'jhi-achat-arrivage-update',
    templateUrl: './achat-arrivage-update.component.html'
})
export class AchatArrivageUpdateComponent implements OnInit {
    achatArrivage: IAchatArrivage;
    isSaving: boolean;

    constructor(private achatArrivageService: AchatArrivageService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatArrivage }) => {
            this.achatArrivage = achatArrivage;
        });
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
}
