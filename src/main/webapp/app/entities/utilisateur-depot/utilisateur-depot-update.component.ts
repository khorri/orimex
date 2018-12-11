import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';
import { UtilisateurDepotService } from './utilisateur-depot.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
    selector: 'jhi-utilisateur-depot-update',
    templateUrl: './utilisateur-depot-update.component.html'
})
export class UtilisateurDepotUpdateComponent implements OnInit {
    utilisateurDepot: IUtilisateurDepot;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurDepotService: UtilisateurDepotService,
        private utilisateurService: UtilisateurService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateurDepot }) => {
            this.utilisateurDepot = utilisateurDepot;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.utilisateurDepot.id !== undefined) {
            this.subscribeToSaveResponse(this.utilisateurDepotService.update(this.utilisateurDepot));
        } else {
            this.subscribeToSaveResponse(this.utilisateurDepotService.create(this.utilisateurDepot));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateurDepot>>) {
        result.subscribe((res: HttpResponse<IUtilisateurDepot>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
