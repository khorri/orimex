import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAchatDevise } from 'app/shared/model/achat-devise.model';
import { AchatDeviseService } from './achat-devise.service';

@Component({
    selector: 'jhi-achat-devise-update',
    templateUrl: './achat-devise-update.component.html'
})
export class AchatDeviseUpdateComponent implements OnInit {
    achatDevise: IAchatDevise;
    isSaving: boolean;

    constructor(private achatDeviseService: AchatDeviseService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatDevise }) => {
            this.achatDevise = achatDevise;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatDevise.id !== undefined) {
            this.subscribeToSaveResponse(this.achatDeviseService.update(this.achatDevise));
        } else {
            this.subscribeToSaveResponse(this.achatDeviseService.create(this.achatDevise));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatDevise>>) {
        result.subscribe((res: HttpResponse<IAchatDevise>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
