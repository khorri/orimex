import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRetour } from 'app/shared/model/retour.model';

@Component({
    selector: 'jhi-retour-detail',
    templateUrl: './retour-detail.component.html'
})
export class RetourDetailComponent implements OnInit {
    retour: IRetour;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ retour }) => {
            this.retour = retour;
        });
    }

    previousState() {
        window.history.back();
    }
}
