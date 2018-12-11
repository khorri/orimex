import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';
import { AchatConteneurReceptionService } from './achat-conteneur-reception.service';
import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';
import { AchatConteneurArrivageService } from 'app/entities/achat-conteneur-arrivage';

@Component({
    selector: 'jhi-achat-conteneur-reception-update',
    templateUrl: './achat-conteneur-reception-update.component.html'
})
export class AchatConteneurReceptionUpdateComponent implements OnInit {
    achatConteneurReception: IAchatConteneurReception;
    isSaving: boolean;

    achatconteneurarrivages: IAchatConteneurArrivage[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatConteneurReceptionService: AchatConteneurReceptionService,
        private achatConteneurArrivageService: AchatConteneurArrivageService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatConteneurReception }) => {
            this.achatConteneurReception = achatConteneurReception;
        });
        this.achatConteneurArrivageService.query({ filter: 'achatconteneurreception-is-null' }).subscribe(
            (res: HttpResponse<IAchatConteneurArrivage[]>) => {
                if (!this.achatConteneurReception.achatConteneurArrivageId) {
                    this.achatconteneurarrivages = res.body;
                } else {
                    this.achatConteneurArrivageService.find(this.achatConteneurReception.achatConteneurArrivageId).subscribe(
                        (subRes: HttpResponse<IAchatConteneurArrivage>) => {
                            this.achatconteneurarrivages = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatConteneurReception.id !== undefined) {
            this.subscribeToSaveResponse(this.achatConteneurReceptionService.update(this.achatConteneurReception));
        } else {
            this.subscribeToSaveResponse(this.achatConteneurReceptionService.create(this.achatConteneurReception));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatConteneurReception>>) {
        result.subscribe(
            (res: HttpResponse<IAchatConteneurReception>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackAchatConteneurArrivageById(index: number, item: IAchatConteneurArrivage) {
        return item.id;
    }
}
