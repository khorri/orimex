import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';

@Component({
    selector: 'jhi-achat-type-paiement-detail',
    templateUrl: './achat-type-paiement-detail.component.html'
})
export class AchatTypePaiementDetailComponent implements OnInit {
    achatTypePaiement: IAchatTypePaiement;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatTypePaiement }) => {
            this.achatTypePaiement = achatTypePaiement;
        });
    }

    previousState() {
        window.history.back();
    }
}
