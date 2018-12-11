import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';

@Component({
    selector: 'jhi-utilisateur-depot-detail',
    templateUrl: './utilisateur-depot-detail.component.html'
})
export class UtilisateurDepotDetailComponent implements OnInit {
    utilisateurDepot: IUtilisateurDepot;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ utilisateurDepot }) => {
            this.utilisateurDepot = utilisateurDepot;
        });
    }

    previousState() {
        window.history.back();
    }
}
