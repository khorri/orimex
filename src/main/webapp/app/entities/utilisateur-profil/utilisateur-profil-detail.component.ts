import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';

@Component({
    selector: 'jhi-utilisateur-profil-detail',
    templateUrl: './utilisateur-profil-detail.component.html'
})
export class UtilisateurProfilDetailComponent implements OnInit {
    utilisateurProfil: IUtilisateurProfil;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurProfil }) => {
            this.utilisateurProfil = utilisateurProfil;
        });
    }

    previousState() {
        window.history.back();
    }
}
