import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';

@Component({
    selector: 'jhi-utilisateur-depot-pk-detail',
    templateUrl: './utilisateur-depot-pk-detail.component.html'
})
export class UtilisateurDepotPKDetailComponent implements OnInit {
    utilisateurDepotPK: IUtilisateurDepotPK;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurDepotPK }) => {
            this.utilisateurDepotPK = utilisateurDepotPK;
        });
    }

    previousState() {
        window.history.back();
    }
}
