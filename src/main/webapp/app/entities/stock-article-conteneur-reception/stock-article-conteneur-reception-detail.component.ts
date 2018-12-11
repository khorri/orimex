import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';

@Component({
    selector: 'jhi-stock-article-conteneur-reception-detail',
    templateUrl: './stock-article-conteneur-reception-detail.component.html'
})
export class StockArticleConteneurReceptionDetailComponent implements OnInit {
    stockArticleConteneurReception: IStockArticleConteneurReception;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stockArticleConteneurReception }) => {
            this.stockArticleConteneurReception = stockArticleConteneurReception;
        });
    }

    previousState() {
        window.history.back();
    }
}
