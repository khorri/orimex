import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRecuperation } from 'app/shared/model/recuperation.model';
import { RecuperationService } from './recuperation.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit';
import { IDepot } from 'app/shared/model/depot.model';
import { DepotService } from 'app/entities/depot';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';

@Component({
    selector: 'jhi-recuperation-update',
    templateUrl: './recuperation-update.component.html'
})
export class RecuperationUpdateComponent implements OnInit {
    recuperation: IRecuperation;
    isSaving: boolean;

    produits: IProduit[];

    depots: IDepot[];

    utilisateurs: IUtilisateur[];
    dateOperation: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private recuperationService: RecuperationService,
        private produitService: ProduitService,
        private depotService: DepotService,
        private utilisateurService: UtilisateurService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ recuperation }) => {
            this.recuperation = recuperation;
            this.dateOperation = this.recuperation.dateOperation != null ? this.recuperation.dateOperation.format(DATE_TIME_FORMAT) : null;
        });
        this.produitService.query().subscribe(
            (res: HttpResponse<IProduit[]>) => {
                this.produits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.depotService.query().subscribe(
            (res: HttpResponse<IDepot[]>) => {
                this.depots = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.utilisateurService.query().subscribe(
            (res: HttpResponse<IUtilisateur[]>) => {
                this.utilisateurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.recuperation.dateOperation = this.dateOperation != null ? moment(this.dateOperation, DATE_TIME_FORMAT) : null;
        if (this.recuperation.id !== undefined) {
            this.subscribeToSaveResponse(this.recuperationService.update(this.recuperation));
        } else {
            this.subscribeToSaveResponse(this.recuperationService.create(this.recuperation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRecuperation>>) {
        result.subscribe((res: HttpResponse<IRecuperation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackProduitById(index: number, item: IProduit) {
        return item.id;
    }

    trackDepotById(index: number, item: IDepot) {
        return item.id;
    }

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }
}
