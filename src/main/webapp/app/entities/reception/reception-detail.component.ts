import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IReception } from 'app/shared/model/reception.model';

@Component({
    selector: 'jhi-reception-detail',
    templateUrl: './reception-detail.component.html'
})
export class ReceptionDetailComponent implements OnInit {
    reception: IReception;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ reception }) => {
            this.reception = reception;
        });
    }

    previousState() {
        window.history.back();
    }
}
