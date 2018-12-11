import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from './produit.service';
import { ICouleur } from 'app/shared/model/couleur.model';
import { CouleurService } from 'app/entities/couleur';
import { IFamilleProduit } from 'app/shared/model/famille-produit.model';
import { FamilleProduitService } from 'app/entities/famille-produit';
import { IOrigine } from 'app/shared/model/origine.model';
import { OrigineService } from 'app/entities/origine';

@Component({
    selector: 'jhi-produit-update',
    templateUrl: './produit-update.component.html'
})
export class ProduitUpdateComponent implements OnInit {
    produit: IProduit;
    isSaving: boolean;

    couleurs: ICouleur[];

    familleproduits: IFamilleProduit[];

    origines: IOrigine[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private produitService: ProduitService,
        private couleurService: CouleurService,
        private familleProduitService: FamilleProduitService,
        private origineService: OrigineService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ produit }) => {
            this.produit = produit;
        });
        this.couleurService.query().subscribe(
            (res: HttpResponse<ICouleur[]>) => {
                this.couleurs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.familleProduitService.query().subscribe(
            (res: HttpResponse<IFamilleProduit[]>) => {
                this.familleproduits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.origineService.query().subscribe(
            (res: HttpResponse<IOrigine[]>) => {
                this.origines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.produit.id !== undefined) {
            this.subscribeToSaveResponse(this.produitService.update(this.produit));
        } else {
            this.subscribeToSaveResponse(this.produitService.create(this.produit));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IProduit>>) {
        result.subscribe((res: HttpResponse<IProduit>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCouleurById(index: number, item: ICouleur) {
        return item.id;
    }

    trackFamilleProduitById(index: number, item: IFamilleProduit) {
        return item.id;
    }

    trackOrigineById(index: number, item: IOrigine) {
        return item.id;
    }
}
