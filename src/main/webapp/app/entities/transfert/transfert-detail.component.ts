import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITransfert } from 'app/shared/model/transfert.model';

@Component({
    selector: 'jhi-transfert-detail',
    templateUrl: './transfert-detail.component.html'
})
export class TransfertDetailComponent implements OnInit {
    transfert: ITransfert;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ transfert }) => {
            this.transfert = transfert;
        });
    }

    previousState() {
        window.history.back();
    }
}
