import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';
import { UtilisateurProfilPKService } from './utilisateur-profil-pk.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { IProfil } from 'app/shared/model/profil.model';
import { ProfilService } from 'app/entities/profil';

@Component({
    selector: 'jhi-utilisateur-profil-pk-update',
    templateUrl: './utilisateur-profil-pk-update.component.html'
})
export class UtilisateurProfilPKUpdateComponent implements OnInit {
    utilisateurProfilPK: IUtilisateurProfilPK;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    profils: IProfil[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurProfilPKService: UtilisateurProfilPKService,
        private utilisateurService: UtilisateurService,
        private profilService: ProfilService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateurProfilPK }) => {
            this.utilisateurProfilPK = utilisateurProfilPK;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
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
        if (this.utilisateurProfilPK.id !== undefined) {
            this.subscribeToSaveResponse(this.utilisateurProfilPKService.update(this.utilisateurProfilPK));
        } else {
            this.subscribeToSaveResponse(this.utilisateurProfilPKService.create(this.utilisateurProfilPK));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateurProfilPK>>) {
        result.subscribe((res: HttpResponse<IUtilisateurProfilPK>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProfilById(index: number, item: IProfil) {
        return item.id;
    }
}
