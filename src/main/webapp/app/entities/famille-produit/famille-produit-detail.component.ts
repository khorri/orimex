import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFamilleProduit } from 'app/shared/model/famille-produit.model';

@Component({
    selector: 'jhi-famille-produit-detail',
    templateUrl: './famille-produit-detail.component.html'
})
export class FamilleProduitDetailComponent implements OnInit {
    familleProduit: IFamilleProduit;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ familleProduit }) => {
            this.familleProduit = familleProduit;
        });
    }

    previousState() {
        window.history.back();
    }
}
