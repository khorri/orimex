import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISortie } from 'app/shared/model/sortie.model';

@Component({
    selector: 'jhi-sortie-detail',
    templateUrl: './sortie-detail.component.html'
})
export class SortieDetailComponent implements OnInit {
    sortie: ISortie;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ sortie }) => {
            this.sortie = sortie;
        });
    }

    previousState() {
        window.history.back();
    }
}
