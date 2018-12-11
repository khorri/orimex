export interface IAchatArticleConteneurArrivage {
    id?: number;
    idArticleConteneurArrivage?: number;
    dimension?: number;
    montant?: number;
    nombreCaissestc?: number;
    nombreFeuillecaisse?: number;
    prixUnitaire?: number;
    quantite?: number;
    poids?: number;
    achatConteneurArrivageId?: number;
    produitId?: number;
}

export class AchatArticleConteneurArrivage implements IAchatArticleConteneurArrivage {
    constructor(
        public id?: number,
        public idArticleConteneurArrivage?: number,
        public dimension?: number,
        public montant?: number,
        public nombreCaissestc?: number,
        public nombreFeuillecaisse?: number,
        public prixUnitaire?: number,
        public quantite?: number,
        public poids?: number,
        public achatConteneurArrivageId?: number,
        public produitId?: number
    ) {}
}
