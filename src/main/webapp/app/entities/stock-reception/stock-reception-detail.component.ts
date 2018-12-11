import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStockReception } from 'app/shared/model/stock-reception.model';

@Component({
    selector: 'jhi-stock-reception-detail',
    templateUrl: './stock-reception-detail.component.html'
})
export class StockReceptionDetailComponent implements OnInit {
    stockReception: IStockReception;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ stockReception }) => {
            this.stockReception = stockReception;
        });
    }

    previousState() {
        window.history.back();
    }
}
