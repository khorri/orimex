import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';

import { IJourFerier } from 'app/shared/model/jour-ferier.model';
import { JourFerierService } from './jour-ferier.service';

@Component({
    selector: 'jhi-jour-ferier-update',
    templateUrl: './jour-ferier-update.component.html'
})
export class JourFerierUpdateComponent implements OnInit {
    jourFerier: IJourFerier;
    isSaving: boolean;
    debutDp: any;
    finDp: any;

    constructor(private jourFerierService: JourFerierService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ jourFerier }) => {
            this.jourFerier = jourFerier;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.jourFerier.id !== undefined) {
            this.subscribeToSaveResponse(this.jourFerierService.update(this.jourFerier));
        } else {
            this.subscribeToSaveResponse(this.jourFerierService.create(this.jourFerier));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IJourFerier>>) {
        result.subscribe((res: HttpResponse<IJourFerier>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
