import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeBon } from 'app/shared/model/type-bon.model';

@Component({
    selector: 'jhi-type-bon-detail',
    templateUrl: './type-bon-detail.component.html'
})
export class TypeBonDetailComponent implements OnInit {
    typeBon: ITypeBon;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ typeBon }) => {
            this.typeBon = typeBon;
        });
    }

    previousState() {
        window.history.back();
    }
}
