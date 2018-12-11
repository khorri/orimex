import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IAuthority } from 'app/shared/model/authority.model';
import { AuthorityService } from './authority.service';

@Component({
    selector: 'jhi-authority-update',
    templateUrl: './authority-update.component.html'
})
export class AuthorityUpdateComponent implements OnInit {
    authority: IAuthority;
    isSaving: boolean;

    constructor(private authorityService: AuthorityService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ authority }) => {
            this.authority = authority;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.authority.id !== undefined) {
            this.subscribeToSaveResponse(this.authorityService.update(this.authority));
        } else {
            this.subscribeToSaveResponse(this.authorityService.create(this.authority));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAuthority>>) {
        result.subscribe((res: HttpResponse<IAuthority>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
