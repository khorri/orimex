import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatBanque } from 'app/shared/model/achat-banque.model';
import { AchatBanqueService } from './achat-banque.service';

@Component({
    selector: 'jhi-achat-banque-update',
    templateUrl: './achat-banque-update.component.html'
})
export class AchatBanqueUpdateComponent implements OnInit {
    achatBanque: IAchatBanque;
    isSaving: boolean;

    constructor(private achatBanqueService: AchatBanqueService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatBanque }) => {
            this.achatBanque = achatBanque;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatBanque.id !== undefined) {
            this.subscribeToSaveResponse(this.achatBanqueService.update(this.achatBanque));
        } else {
            this.subscribeToSaveResponse(this.achatBanqueService.create(this.achatBanque));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatBanque>>) {
        result.subscribe((res: HttpResponse<IAchatBanque>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
