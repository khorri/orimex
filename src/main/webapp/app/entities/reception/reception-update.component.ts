import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IReception } from 'app/shared/model/reception.model';
import { ReceptionService } from './reception.service';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { IBon } from 'app/shared/model/bon.model';
import { BonService } from 'app/entities/bon';
import { ICaisse } from 'app/shared/model/caisse.model';
import { CaisseService } from 'app/entities/caisse';
import { ICamion } from 'app/shared/model/camion.model';
import { CamionService } from 'app/entities/camion';
import { IConteneur } from 'app/shared/model/conteneur.model';
import { ConteneurService } from 'app/entities/conteneur';
import { IDepot } from 'app/shared/model/depot.model';
import { DepotService } from 'app/entities/depot';

@Component({
    selector: 'jhi-reception-update',
    templateUrl: './reception-update.component.html'
})
export class ReceptionUpdateComponent implements OnInit {
    reception: IReception;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    bons: IBon[];

    caisses: ICaisse[];

    camions: ICamion[];

    conteneurs: IConteneur[];

    depots: IDepot[];
    dateReception: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private receptionService: ReceptionService,
        private utilisateurService: UtilisateurService,
        private bonService: BonService,
        private caisseService: CaisseService,
        private camionService: CamionService,
        private conteneurService: ConteneurService,
        private depotService: DepotService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ reception }) => {
            this.reception = reception;
            this.dateReception = this.reception.dateReception != null ? this.reception.dateReception.format(DATE_TIME_FORMAT) : null;
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
        this.camionService.query().subscribe(
            (res: HttpResponse<ICamion[]>) => {
                this.camions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.conteneurService.query().subscribe(
            (res: HttpResponse<IConteneur[]>) => {
                this.conteneurs = res.body;
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
        this.reception.dateReception = this.dateReception != null ? moment(this.dateReception, DATE_TIME_FORMAT) : null;
        if (this.reception.id !== undefined) {
            this.subscribeToSaveResponse(this.receptionService.update(this.reception));
        } else {
            this.subscribeToSaveResponse(this.receptionService.create(this.reception));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IReception>>) {
        result.subscribe((res: HttpResponse<IReception>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCamionById(index: number, item: ICamion) {
        return item.id;
    }

    trackConteneurById(index: number, item: IConteneur) {
        return item.id;
    }

    trackDepotById(index: number, item: IDepot) {
        return item.id;
    }
}
