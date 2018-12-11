import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';
import { UtilisateurDepotPKService } from './utilisateur-depot-pk.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { IDepot } from 'app/shared/model/depot.model';
import { DepotService } from 'app/entities/depot';

@Component({
    selector: 'jhi-utilisateur-depot-pk-update',
    templateUrl: './utilisateur-depot-pk-update.component.html'
})
export class UtilisateurDepotPKUpdateComponent implements OnInit {
    utilisateurDepotPK: IUtilisateurDepotPK;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    depots: IDepot[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurDepotPKService: UtilisateurDepotPKService,
        private utilisateurService: UtilisateurService,
        private depotService: DepotService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateurDepotPK }) => {
            this.utilisateurDepotPK = utilisateurDepotPK;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.depotService.query().subscribe(
            (res: HttpResponse<IDepot[]>) => {
                this.depots = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.utilisateurDepotPK.id !== undefined) {
            this.subscribeToSaveResponse(this.utilisateurDepotPKService.update(this.utilisateurDepotPK));
        } else {
            this.subscribeToSaveResponse(this.utilisateurDepotPKService.create(this.utilisateurDepotPK));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateurDepotPK>>) {
        result.subscribe((res: HttpResponse<IUtilisateurDepotPK>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }

    trackDepotById(index: number, item: IDepot) {
        return item.id;
    }
}
