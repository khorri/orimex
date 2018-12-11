import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAchatDevise } from 'app/shared/model/achat-devise.model';

@Component({
    selector: 'jhi-achat-devise-detail',
    templateUrl: './achat-devise-detail.component.html'
})
export class AchatDeviseDetailComponent implements OnInit {
    achatDevise: IAchatDevise;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ achatDevise }) => {
            this.achatDevise = achatDevise;
        });
    }

    previousState() {
        window.history.back();
    }
}
