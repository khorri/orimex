import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IRetour } from 'app/shared/model/retour.model';
import { RetourService } from './retour.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { UtilisateurService } from 'app/entities/utilisateur';
import { IBon } from 'app/shared/model/bon.model';
import { BonService } from 'app/entities/bon';

@Component({
    selector: 'jhi-retour-update',
    templateUrl: './retour-update.component.html'
})
export class RetourUpdateComponent implements OnInit {
    retour: IRetour;
    isSaving: boolean;

    produits: IProduit[];

    utilisateurs: IUtilisateur[];

    bons: IBon[];
    dateOperation: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private retourService: RetourService,
        private produitService: ProduitService,
        private utilisateurService: UtilisateurService,
        private bonService: BonService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ retour }) => {
            this.retour = retour;
            this.dateOperation = this.retour.dateOperation != null ? this.retour.dateOperation.format(DATE_TIME_FORMAT) : null;
        });
        this.produitService.query().subscribe(
            (res: HttpResponse<IProduit[]>) => {
                this.produits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
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
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.retour.dateOperation = this.dateOperation != null ? moment(this.dateOperation, DATE_TIME_FORMAT) : null;
        if (this.retour.id !== undefined) {
            this.subscribeToSaveResponse(this.retourService.update(this.retour));
        } else {
            this.subscribeToSaveResponse(this.retourService.create(this.retour));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRetour>>) {
        result.subscribe((res: HttpResponse<IRetour>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUtilisateurById(index: number, item: IUtilisateur) {
        return item.id;
    }

    trackBonById(index: number, item: IBon) {
        return item.id;
    }
}
