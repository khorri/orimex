import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatBanque } from 'app/shared/model/achat-banque.model';

@Component({
    selector: 'jhi-achat-banque-detail',
    templateUrl: './achat-banque-detail.component.html'
})
export class AchatBanqueDetailComponent implements OnInit {
    achatBanque: IAchatBanque;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatBanque }) => {
            this.achatBanque = achatBanque;
        });
    }

    previousState() {
        window.history.back();
    }
}
