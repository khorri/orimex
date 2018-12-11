import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IAchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';
import { AchatArticleLigneProformaService } from './achat-article-ligne-proforma.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit';
import { IAchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';
import { AchatLigneProformaService } from 'app/entities/achat-ligne-proforma';

@Component({
    selector: 'jhi-achat-article-ligne-proforma-update',
    templateUrl: './achat-article-ligne-proforma-update.component.html'
})
export class AchatArticleLigneProformaUpdateComponent implements OnInit {
    achatArticleLigneProforma: IAchatArticleLigneProforma;
    isSaving: boolean;

    produits: IProduit[];

    achatligneproformas: IAchatLigneProforma[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private achatArticleLigneProformaService: AchatArticleLigneProformaService,
        private produitService: ProduitService,
        private achatLigneProformaService: AchatLigneProformaService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ achatArticleLigneProforma }) => {
            this.achatArticleLigneProforma = achatArticleLigneProforma;
        });
        this.produitService.query().subscribe(
            (res: HttpResponse<IProduit[]>) => {
                this.produits = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.achatLigneProformaService.query().subscribe(
            (res: HttpResponse<IAchatLigneProforma[]>) => {
                this.achatligneproformas = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.achatArticleLigneProforma.id !== undefined) {
            this.subscribeToSaveResponse(this.achatArticleLigneProformaService.update(this.achatArticleLigneProforma));
        } else {
            this.subscribeToSaveResponse(this.achatArticleLigneProformaService.create(this.achatArticleLigneProforma));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IAchatArticleLigneProforma>>) {
        result.subscribe(
            (res: HttpResponse<IAchatArticleLigneProforma>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
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

    trackAchatLigneProformaById(index: number, item: IAchatLigneProforma) {
        return item.id;
    }
}
