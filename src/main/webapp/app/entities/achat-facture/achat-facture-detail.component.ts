import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatFacture } from 'app/shared/model/achat-facture.model';

@Component({
    selector: 'jhi-achat-facture-detail',
    templateUrl: './achat-facture-detail.component.html'
})
export class AchatFactureDetailComponent implements OnInit {
    achatFacture: IAchatFacture;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatFacture }) => {
            this.achatFacture = achatFacture;
        });
    }

    previousState() {
        window.history.back();
    }
}
