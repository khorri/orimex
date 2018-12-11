import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrigine } from 'app/shared/model/origine.model';

@Component({
    selector: 'jhi-origine-detail',
    templateUrl: './origine-detail.component.html'
})
export class OrigineDetailComponent implements OnInit {
    origine: IOrigine;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ origine }) => {
            this.origine = origine;
        });
    }

    previousState() {
        window.history.back();
    }
}
