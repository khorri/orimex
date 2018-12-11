import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilAction } from 'app/shared/model/profil-action.model';

@Component({
    selector: 'jhi-profil-action-detail',
    templateUrl: './profil-action-detail.component.html'
})
export class ProfilActionDetailComponent implements OnInit {
    profilAction: IProfilAction;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilAction }) => {
            this.profilAction = profilAction;
        });
    }

    previousState() {
        window.history.back();
    }
}
