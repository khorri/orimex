import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';

@Component({
    selector: 'jhi-achat-ligne-proforma-detail',
    templateUrl: './achat-ligne-proforma-detail.component.html'
})
export class AchatLigneProformaDetailComponent implements OnInit {
    achatLigneProforma: IAchatLigneProforma;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatLigneProforma }) => {
            this.achatLigneProforma = achatLigneProforma;
        });
    }

    previousState() {
        window.history.back();
    }
}
