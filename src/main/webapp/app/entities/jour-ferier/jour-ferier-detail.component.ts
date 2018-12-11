import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJourFerier } from 'app/shared/model/jour-ferier.model';

@Component({
    selector: 'jhi-jour-ferier-detail',
    templateUrl: './jour-ferier-detail.component.html'
})
export class JourFerierDetailComponent implements OnInit {
    jourFerier: IJourFerier;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ jourFerier }) => {
            this.jourFerier = jourFerier;
        });
    }

    previousState() {
        window.history.back();
    }
}
