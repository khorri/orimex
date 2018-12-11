import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ISortie } from 'app/shared/model/sortie.model';
import { SortieService } from './sortie.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { IBon } from 'app/shared/model/bon.model';
import { BonService } from 'app/entities/bon';
import { ICaisse } from 'app/shared/model/caisse.model';
import { CaisseService } from 'app/entities/caisse';
import { IDepot } from 'app/shared/model/depot.model';
import { DepotService } from 'app/entities/depot';

@Component({
    selector: 'jhi-sortie-update',
    templateUrl: './sortie-update.component.html'
})
export class SortieUpdateComponent implements OnInit {
    sortie: ISortie;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    bons: IBon[];

    caisses: ICaisse[];

    depots: IDepot[];
    dateOperation: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private sortieService: SortieService,
        private utilisateurService: UtilisateurService,
        private bonService: BonService,
        private caisseService: CaisseService,
        private depotService: DepotService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ sortie }) => {
            this.sortie = sortie;
            this.dateOperation = this.sortie.dateOperation != null ? this.sortie.dateOperation.format(DATE_TIME_FORMAT) : null;
        });
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.bonService.query().subscribe(
            (res: HttpResponse<IBon[]>) => {
                this.bons = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.caisseService.query().subscribe(
            (res: HttpResponse<ICaisse[]>) => {
                this.caisses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.depotService.query().subscribe(
            (res: HttpResponse<IDepot[]>) => {
                this.depots = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.sortie.dateOperation = this.dateOperation != null ? moment(this.dateOperation, DATE_TIME_FORMAT) : null;
        if (this.sortie.id !== undefined) {
            this.subscribeToSaveResponse(this.sortieService.update(this.sortie));
        } else {
            this.subscribeToSaveResponse(this.sortieService.create(this.sortie));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISortie>>) {
        result.subscribe((res: HttpResponse<ISortie>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackBonById(index: number, item: IBon) {
        return item.id;
    }

    trackCaisseById(index: number, item: ICaisse) {
        return item.id;
    }

    trackDepotById(index: number, item: IDepot) {
        return item.id;
    }
}
