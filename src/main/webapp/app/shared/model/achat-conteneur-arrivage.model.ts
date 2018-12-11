import { IAchatArticleConteneurArrivage } from 'app/shared/model//achat-article-conteneur-arrivage.model';

export interface IAchatConteneurArrivage {
    id?: number;
    idConteneurArrivage?: number;
    montant?: number;
    numeroConteneurs?: string;
    numeroSequence?: number;
    poids?: number;
    quantite?: number;
    achatFactureId?: number;
    stockConteneurReceptionId?: number;
    achatArticleConteneurArrivages?: IAchatArticleConteneurArrivage[];
}

export class AchatConteneurArrivage implements IAchatConteneurArrivage {
    constructor(
        public id?: number,
        public idConteneurArrivage?: number,
        public montant?: number,
        public numeroConteneurs?: string,
        public numeroSequence?: number,
        public poids?: number,
        public quantite?: number,
        public achatFactureId?: number,
        public stockConteneurReceptionId?: number,
        public achatArticleConteneurArrivages?: IAchatArticleConteneurArrivage[]
    ) {}
}
