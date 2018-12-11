import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ITypeBon } from 'app/shared/model/type-bon.model';
import { TypeBonService } from './type-bon.service';

@Component({
    selector: 'jhi-type-bon-update',
    templateUrl: './type-bon-update.component.html'
})
export class TypeBonUpdateComponent implements OnInit {
    typeBon: ITypeBon;
    isSaving: boolean;

    constructor(private typeBonService: TypeBonService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ typeBon }) => {
            this.typeBon = typeBon;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.typeBon.id !== undefined) {
            this.subscribeToSaveResponse(this.typeBonService.update(this.typeBon));
        } else {
            this.subscribeToSaveResponse(this.typeBonService.create(this.typeBon));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITypeBon>>) {
        result.subscribe((res: HttpResponse<ITypeBon>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
