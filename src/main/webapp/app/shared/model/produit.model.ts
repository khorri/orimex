import { IRecuperation } from 'app/shared/model//recuperation.model';
import { IRetour } from 'app/shared/model//retour.model';
import { IAchatArticleLigneProforma } from 'app/shared/model//achat-article-ligne-proforma.model';
import { IAchatArticleConteneurArrivage } from 'app/shared/model//achat-article-conteneur-arrivage.model';

export interface IProduit {
    id?: number;
    idProduit?: number;
    designationProduit?: string;
    dimension?: string;
    epaisseur?: string;
    largeure?: number;
    longueur?: number;
    referenceProduit?: string;
    poids?: number;
    libelleCouleur?: string;
    libelleFamille?: string;
    libelleOrigine?: string;
    recuperations?: IRecuperation[];
    retours?: IRetour[];
    achatArticleLigneProformas?: IAchatArticleLigneProforma[];
    couleurId?: number;
    familleProduitId?: number;
    origineId?: number;
    achatArticleConteneurArrivages?: IAchatArticleConteneurArrivage[];
}

export class Produit implements IProduit {
    constructor(
        public id?: number,
        public idProduit?: number,
        public designationProduit?: string,
        public dimension?: string,
        public epaisseur?: string,
        public largeure?: number,
        public longueur?: number,
        public referenceProduit?: string,
        public poids?: number,
        public libelleCouleur?: string,
        public libelleFamille?: string,
        public libelleOrigine?: string,
        public recuperations?: IRecuperation[],
        public retours?: IRetour[],
        public achatArticleLigneProformas?: IAchatArticleLigneProforma[],
        public couleurId?: number,
        public familleProduitId?: number,
        public origineId?: number,
        public achatArticleConteneurArrivages?: IAchatArticleConteneurArrivage[]
    ) {}
}
