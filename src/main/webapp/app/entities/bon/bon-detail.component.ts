import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBon } from 'app/shared/model/bon.model';

@Component({
    selector: 'jhi-bon-detail',
    templateUrl: './bon-detail.component.html'
})
export class BonDetailComponent implements OnInit {
    bon: IBon;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ bon }) => {
            this.bon = bon;
        });
    }

    previousState() {
        window.history.back();
    }
}
