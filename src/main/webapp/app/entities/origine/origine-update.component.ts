import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IOrigine } from 'app/shared/model/origine.model';
import { OrigineService } from './origine.service';

@Component({
    selector: 'jhi-origine-update',
    templateUrl: './origine-update.component.html'
})
export class OrigineUpdateComponent implements OnInit {
    origine: IOrigine;
    isSaving: boolean;

    constructor(private origineService: OrigineService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ origine }) => {
            this.origine = origine;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.origine.id !== undefined) {
            this.subscribeToSaveResponse(this.origineService.update(this.origine));
        } else {
            this.subscribeToSaveResponse(this.origineService.create(this.origine));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IOrigine>>) {
        result.subscribe((res: HttpResponse<IOrigine>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
