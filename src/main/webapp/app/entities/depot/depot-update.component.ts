import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IDepot } from 'app/shared/model/depot.model';
import { DepotService } from './depot.service';
import { IVille } from 'app/shared/model/ville.model';
import { VilleService } from 'app/entities/ville';

@Component({
    selector: 'jhi-depot-update',
    templateUrl: './depot-update.component.html'
})
export class DepotUpdateComponent implements OnInit {
    depot: IDepot;
    isSaving: boolean;

    villes: IVille[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private depotService: DepotService,
        private villeService: VilleService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ depot }) => {
            this.depot = depot;
        });
        this.villeService.query().subscribe(
            (res: HttpResponse<IVille[]>) => {
                this.villes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.depot.id !== undefined) {
            this.subscribeToSaveResponse(this.depotService.update(this.depot));
        } else {
            this.subscribeToSaveResponse(this.depotService.create(this.depot));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDepot>>) {
        result.subscribe((res: HttpResponse<IDepot>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackVilleById(index: number, item: IVille) {
        return item.id;
    }
}
