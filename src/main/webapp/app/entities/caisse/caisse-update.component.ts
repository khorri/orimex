import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICaisse } from 'app/shared/model/caisse.model';
import { CaisseService } from './caisse.service';

@Component({
    selector: 'jhi-caisse-update',
    templateUrl: './caisse-update.component.html'
})
export class CaisseUpdateComponent implements OnInit {
    caisse: ICaisse;
    isSaving: boolean;

    constructor(private caisseService: CaisseService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ caisse }) => {
            this.caisse = caisse;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.caisse.id !== undefined) {
            this.subscribeToSaveResponse(this.caisseService.update(this.caisse));
        } else {
            this.subscribeToSaveResponse(this.caisseService.create(this.caisse));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICaisse>>) {
        result.subscribe((res: HttpResponse<ICaisse>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
