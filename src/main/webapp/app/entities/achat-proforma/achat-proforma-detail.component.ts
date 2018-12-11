import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatProforma } from 'app/shared/model/achat-proforma.model';

@Component({
    selector: 'jhi-achat-proforma-detail',
    templateUrl: './achat-proforma-detail.component.html'
})
export class AchatProformaDetailComponent implements OnInit {
    achatProforma: IAchatProforma;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatProforma }) => {
            this.achatProforma = achatProforma;
        });
    }

    previousState() {
        window.history.back();
    }
}
