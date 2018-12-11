import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ICasse } from 'app/shared/model/casse.model';
import { CasseService } from './casse.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { ICaisse } from 'app/shared/model/caisse.model';
import { CaisseService } from 'app/entities/caisse';

@Component({
    selector: 'jhi-casse-update',
    templateUrl: './casse-update.component.html'
})
export class CasseUpdateComponent implements OnInit {
    casse: ICasse;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    caisses: ICaisse[];
    dateOperation: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private casseService: CasseService,
        private utilisateurService: UtilisateurService,
        private caisseService: CaisseService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ casse }) => {
            this.casse = casse;
            this.dateOperation = this.casse.dateOperation != null ? this.casse.dateOperation.format(DATE_TIME_FORMAT) : null;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.caisseService.query().subscribe(
            (res: HttpResponse<ICaisse[]>) => {
                this.caisses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.casse.dateOperation = this.dateOperation != null ? moment(this.dateOperation, DATE_TIME_FORMAT) : null;
        if (this.casse.id !== undefined) {
            this.subscribeToSaveResponse(this.casseService.update(this.casse));
        } else {
            this.subscribeToSaveResponse(this.casseService.create(this.casse));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICasse>>) {
        result.subscribe((res: HttpResponse<ICasse>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }

    trackCaisseById(index: number, item: ICaisse) {
        return item.id;
    }
}
