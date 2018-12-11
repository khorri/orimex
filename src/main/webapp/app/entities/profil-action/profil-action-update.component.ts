import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProfilAction } from 'app/shared/model/profil-action.model';
import { ProfilActionService } from './profil-action.service';
import { IAction } from 'app/shared/model/action.model';
import { ActionService } from 'app/entities/action';
import { IProfil } from 'app/shared/model/profil.model';
import { ProfilService } from 'app/entities/profil';

@Component({
    selector: 'jhi-profil-action-update',
    templateUrl: './profil-action-update.component.html'
})
export class ProfilActionUpdateComponent implements OnInit {
    profilAction: IProfilAction;
    isSaving: boolean;

    actions: IAction[];

    profils: IProfil[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private profilActionService: ProfilActionService,
        private actionService: ActionService,
        private profilService: ProfilService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profilAction }) => {
            this.profilAction = profilAction;
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
        if (this.profilAction.id !== undefined) {
            this.subscribeToSaveResponse(this.profilActionService.update(this.profilAction));
        } else {
            this.subscribeToSaveResponse(this.profilActionService.create(this.profilAction));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfilAction>>) {
        result.subscribe((res: HttpResponse<IProfilAction>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
