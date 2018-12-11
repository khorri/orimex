import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';

@Component({
    selector: 'jhi-utilisateur-profil-pk-detail',
    templateUrl: './utilisateur-profil-pk-detail.component.html'
})
export class UtilisateurProfilPKDetailComponent implements OnInit {
    utilisateurProfilPK: IUtilisateurProfilPK;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurProfilPK }) => {
            this.utilisateurProfilPK = utilisateurProfilPK;
        });
    }

    previousState() {
        window.history.back();
    }
}
