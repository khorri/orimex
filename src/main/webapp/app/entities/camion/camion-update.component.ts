import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICamion } from 'app/shared/model/camion.model';
import { CamionService } from './camion.service';

@Component({
    selector: 'jhi-camion-update',
    templateUrl: './camion-update.component.html'
})
export class CamionUpdateComponent implements OnInit {
    camion: ICamion;
    isSaving: boolean;

    constructor(private camionService: CamionService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ camion }) => {
            this.camion = camion;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.camion.id !== undefined) {
            this.subscribeToSaveResponse(this.camionService.update(this.camion));
        } else {
            this.subscribeToSaveResponse(this.camionService.create(this.camion));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICamion>>) {
        result.subscribe((res: HttpResponse<ICamion>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
