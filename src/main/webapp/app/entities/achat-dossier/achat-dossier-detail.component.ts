import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatDossier } from 'app/shared/model/achat-dossier.model';

@Component({
    selector: 'jhi-achat-dossier-detail',
    templateUrl: './achat-dossier-detail.component.html'
})
export class AchatDossierDetailComponent implements OnInit {
    achatDossier: IAchatDossier;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatDossier }) => {
            this.achatDossier = achatDossier;
        });
    }

    previousState() {
        window.history.back();
    }
}
