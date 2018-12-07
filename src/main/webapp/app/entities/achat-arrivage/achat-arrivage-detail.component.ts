import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';

@Component({
    selector: 'jhi-achat-arrivage-detail',
    templateUrl: './achat-arrivage-detail.component.html'
})
export class AchatArrivageDetailComponent implements OnInit {
    achatArrivage: IAchatArrivage;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArrivage }) => {
            this.achatArrivage = achatArrivage;
        });
    }

    previousState() {
        window.history.back();
    }
}
