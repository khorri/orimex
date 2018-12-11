import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProfilActionPK } from 'app/shared/model/profil-action-pk.model';

@Component({
    selector: 'jhi-profil-action-pk-detail',
    templateUrl: './profil-action-pk-detail.component.html'
})
export class ProfilActionPKDetailComponent implements OnInit {
    profilActionPK: IProfilActionPK;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ profilActionPK }) => {
            this.profilActionPK = profilActionPK;
        });
    }

    previousState() {
        window.history.back();
    }
}
