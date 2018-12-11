import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';

@Component({
    selector: 'jhi-achat-conteneur-reception-detail',
    templateUrl: './achat-conteneur-reception-detail.component.html'
})
export class AchatConteneurReceptionDetailComponent implements OnInit {
    achatConteneurReception: IAchatConteneurReception;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatConteneurReception }) => {
            this.achatConteneurReception = achatConteneurReception;
        });
    }

    previousState() {
        window.history.back();
    }
}
