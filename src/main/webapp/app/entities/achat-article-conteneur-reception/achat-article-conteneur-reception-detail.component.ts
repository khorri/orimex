import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';

@Component({
    selector: 'jhi-achat-article-conteneur-reception-detail',
    templateUrl: './achat-article-conteneur-reception-detail.component.html'
})
export class AchatArticleConteneurReceptionDetailComponent implements OnInit {
    achatArticleConteneurReception: IAchatArticleConteneurReception;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArticleConteneurReception }) => {
            this.achatArticleConteneurReception = achatArticleConteneurReception;
        });
    }

    previousState() {
        window.history.back();
    }
}
