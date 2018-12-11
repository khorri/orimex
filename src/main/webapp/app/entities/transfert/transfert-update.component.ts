import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { ITransfert } from 'app/shared/model/transfert.model';
import { TransfertService } from './transfert.service';
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
    selector: 'jhi-transfert-update',
    templateUrl: './transfert-update.component.html'
})
export class TransfertUpdateComponent implements OnInit {
    transfert: ITransfert;
    isSaving: boolean;

    utilisateurs: IUtilisateur[];

    bons: IBon[];

    caisses: ICaisse[];

    camions: ICamion[];

    conteneurs: IConteneur[];

    depots: IDepot[];
    dateOperation: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private transfertService: TransfertService,
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
        this.activatedRoute.data.subscribe(({ transfert }) => {
            this.transfert = transfert;
            this.dateOperation = this.transfert.dateOperation != null ? this.transfert.dateOperation.format(DATE_TIME_FORMAT) : null;
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
        this.transfert.dateOperation = this.dateOperation != null ? moment(this.dateOperation, DATE_TIME_FORMAT) : null;
        if (this.transfert.id !== undefined) {
            this.subscribeToSaveResponse(this.transfertService.update(this.transfert));
        } else {
            this.subscribeToSaveResponse(this.transfertService.create(this.transfert));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITransfert>>) {
        result.subscribe((res: HttpResponse<ITransfert>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
