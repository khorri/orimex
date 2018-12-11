import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IBon } from 'app/shared/model/bon.model';
import { BonService } from './bon.service';

@Component({
    selector: 'jhi-bon-update',
    templateUrl: './bon-update.component.html'
})
export class BonUpdateComponent implements OnInit {
    bon: IBon;
    isSaving: boolean;

    constructor(private bonService: BonService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ bon }) => {
            this.bon = bon;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.bon.id !== undefined) {
            this.subscribeToSaveResponse(this.bonService.update(this.bon));
        } else {
            this.subscribeToSaveResponse(this.bonService.create(this.bon));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBon>>) {
        result.subscribe((res: HttpResponse<IBon>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
