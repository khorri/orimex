import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRecuperation } from 'app/shared/model/recuperation.model';

@Component({
    selector: 'jhi-recuperation-detail',
    templateUrl: './recuperation-detail.component.html'
})
export class RecuperationDetailComponent implements OnInit {
    recuperation: IRecuperation;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ recuperation }) => {
            this.recuperation = recuperation;
        });
    }

    previousState() {
        window.history.back();
    }
}
