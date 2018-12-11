import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICaisse } from 'app/shared/model/caisse.model';

@Component({
    selector: 'jhi-caisse-detail',
    templateUrl: './caisse-detail.component.html'
})
export class CaisseDetailComponent implements OnInit {
    caisse: ICaisse;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ caisse }) => {
            this.caisse = caisse;
        });
    }

    previousState() {
        window.history.back();
    }
}
