import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';

@Component({
    selector: 'jhi-achat-article-conteneur-arrivage-detail',
    templateUrl: './achat-article-conteneur-arrivage-detail.component.html'
})
export class AchatArticleConteneurArrivageDetailComponent implements OnInit {
    achatArticleConteneurArrivage: IAchatArticleConteneurArrivage;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArticleConteneurArrivage }) => {
            this.achatArticleConteneurArrivage = achatArticleConteneurArrivage;
        });
    }

    previousState() {
        window.history.back();
    }
}
