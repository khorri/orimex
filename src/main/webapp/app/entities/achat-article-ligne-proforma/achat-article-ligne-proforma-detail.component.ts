import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';

@Component({
    selector: 'jhi-achat-article-ligne-proforma-detail',
    templateUrl: './achat-article-ligne-proforma-detail.component.html'
})
export class AchatArticleLigneProformaDetailComponent implements OnInit {
    achatArticleLigneProforma: IAchatArticleLigneProforma;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatArticleLigneProforma }) => {
            this.achatArticleLigneProforma = achatArticleLigneProforma;
        });
    }

    previousState() {
        window.history.back();
    }
}
