import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';

@Component({
    selector: 'jhi-achat-prevision-arrivage-detail',
    templateUrl: './achat-prevision-arrivage-detail.component.html'
})
export class AchatPrevisionArrivageDetailComponent implements OnInit {
    achatPrevisionArrivage: IAchatPrevisionArrivage;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatPrevisionArrivage }) => {
            this.achatPrevisionArrivage = achatPrevisionArrivage;
        });
    }

    previousState() {
        window.history.back();
    }
}
