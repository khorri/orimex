import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IProfil } from 'app/shared/model/profil.model';
import { ProfilService } from './profil.service';

@Component({
    selector: 'jhi-profil-update',
    templateUrl: './profil-update.component.html'
})
export class ProfilUpdateComponent implements OnInit {
    profil: IProfil;
    isSaving: boolean;

    constructor(private profilService: ProfilService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ profil }) => {
            this.profil = profil;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.profil.id !== undefined) {
            this.subscribeToSaveResponse(this.profilService.update(this.profil));
        } else {
            this.subscribeToSaveResponse(this.profilService.create(this.profil));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProfil>>) {
        result.subscribe((res: HttpResponse<IProfil>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
