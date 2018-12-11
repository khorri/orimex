import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';
import { UtilisateurProfilService } from './utilisateur-profil.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { IProfil } from 'app/shared/model/profil.model';
import { ProfilService } from 'app/entities/profil';

@Component({
    selector: 'jhi-utilisateur-profil-update',
    templateUrl: './utilisateur-profil-update.component.html'
})
export class UtilisateurProfilUpdateComponent implements OnInit {
    utilisateurProfil: IUtilisateurProfil;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    profils: IProfil[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private utilisateurProfilService: UtilisateurProfilService,
        private utilisateurService: UtilisateurService,
        private profilService: ProfilService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ utilisateurProfil }) => {
            this.utilisateurProfil = utilisateurProfil;
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
        if (this.utilisateurProfil.id !== undefined) {
            this.subscribeToSaveResponse(this.utilisateurProfilService.update(this.utilisateurProfil));
        } else {
            this.subscribeToSaveResponse(this.utilisateurProfilService.create(this.utilisateurProfil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUtilisateurProfil>>) {
        result.subscribe((res: HttpResponse<IUtilisateurProfil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
