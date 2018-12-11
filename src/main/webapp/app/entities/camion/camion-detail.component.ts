import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICamion } from 'app/shared/model/camion.model';

@Component({
    selector: 'jhi-camion-detail',
    templateUrl: './camion-detail.component.html'
})
export class CamionDetailComponent implements OnInit {
    camion: ICamion;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ camion }) => {
            this.camion = camion;
        });
    }

    previousState() {
        window.history.back();
    }
}
