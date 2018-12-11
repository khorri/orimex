import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICasse } from 'app/shared/model/casse.model';

@Component({
    selector: 'jhi-casse-detail',
    templateUrl: './casse-detail.component.html'
})
export class CasseDetailComponent implements OnInit {
    casse: ICasse;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ casse }) => {
            this.casse = casse;
        });
    }

    previousState() {
        window.history.back();
    }
}
