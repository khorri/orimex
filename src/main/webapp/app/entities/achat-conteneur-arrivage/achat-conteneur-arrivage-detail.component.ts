import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';

@Component({
    selector: 'jhi-achat-conteneur-arrivage-detail',
    templateUrl: './achat-conteneur-arrivage-detail.component.html'
})
export class AchatConteneurArrivageDetailComponent implements OnInit {
    achatConteneurArrivage: IAchatConteneurArrivage;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatConteneurArrivage }) => {
            this.achatConteneurArrivage = achatConteneurArrivage;
        });
    }

    previousState() {
        window.history.back();
    }
}
