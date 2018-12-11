import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProfilActionPK } from 'app/shared/model/profil-action-pk.model';
import { ProfilActionPKService } from './profil-action-pk.service';
import { IAction } from 'app/shared/model/action.model';
import { ActionService } from 'app/entities/action';
import { IProfil } from 'app/shared/model/profil.model';
import { ProfilService } from 'app/entities/profil';

@Component({
    selector: 'jhi-profil-action-pk-update',
    templateUrl: './profil-action-pk-update.component.html'
})
export class ProfilActionPKUpdateComponent implements OnInit {
    profilActionPK: IProfilActionPK;
    isSaving: boolean;

    actions: IAction[];

    profils: IProfil[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private profilActionPKService: ProfilActionPKService,
        private actionService: ActionService,
        private profilService: ProfilService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profilActionPK }) => {
            this.profilActionPK = profilActionPK;
        });
        this.actionService.query().subscribe(
            (res: HttpResponse<IAction[]>) => {
                this.actions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.profilService.query().subscribe(
            (res: HttpResponse<IProfil[]>) => {
                this.profils = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.profilActionPK.id !== undefined) {
            this.subscribeToSaveResponse(this.profilActionPKService.update(this.profilActionPK));
        } else {
            this.subscribeToSaveResponse(this.profilActionPKService.create(this.profilActionPK));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfilActionPK>>) {
        result.subscribe((res: HttpResponse<IProfilActionPK>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackActionById(index: number, item: IAction) {
        return item.id;
    }

    trackProfilById(index: number, item: IProfil) {
        return item.id;
    }
}
