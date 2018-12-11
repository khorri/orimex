import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';

@Component({
    selector: 'jhi-achat-statut-dossier-detail',
    templateUrl: './achat-statut-dossier-detail.component.html'
})
export class AchatStatutDossierDetailComponent implements OnInit {
    achatStatutDossier: IAchatStatutDossier;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatStatutDossier }) => {
            this.achatStatutDossier = achatStatutDossier;
        });
    }

    previousState() {
        window.history.back();
    }
}
