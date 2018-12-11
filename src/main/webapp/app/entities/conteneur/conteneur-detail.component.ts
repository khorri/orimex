import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConteneur } from 'app/shared/model/conteneur.model';

@Component({
    selector: 'jhi-conteneur-detail',
    templateUrl: './conteneur-detail.component.html'
})
export class ConteneurDetailComponent implements OnInit {
    conteneur: IConteneur;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ conteneur }) => {
            this.conteneur = conteneur;
        });
    }

    previousState() {
        window.history.back();
    }
}
