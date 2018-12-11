import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatFournisseur } from 'app/shared/model/achat-fournisseur.model';

@Component({
    selector: 'jhi-achat-fournisseur-detail',
    templateUrl: './achat-fournisseur-detail.component.html'
})
export class AchatFournisseurDetailComponent implements OnInit {
    achatFournisseur: IAchatFournisseur;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatFournisseur }) => {
            this.achatFournisseur = achatFournisseur;
        });
    }

    previousState() {
        window.history.back();
    }
}
