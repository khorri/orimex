import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';

@Component({
    selector: 'jhi-stock-conteneur-reception-detail',
    templateUrl: './stock-conteneur-reception-detail.component.html'
})
export class StockConteneurReceptionDetailComponent implements OnInit {
    stockConteneurReception: IStockConteneurReception;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stockConteneurReception }) => {
            this.stockConteneurReception = stockConteneurReception;
        });
    }

    previousState() {
        window.history.back();
    }
}
