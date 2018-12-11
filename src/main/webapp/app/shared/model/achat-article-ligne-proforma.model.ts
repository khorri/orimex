export interface IAchatArticleLigneProforma {
    id?: number;
    idArticleLigneProforma?: number;
    numeroSequence?: number;
    nombreCaissesTc?: number;
    nombreFeuillesCaisse?: number;
    dimention?: number;
    quantite?: number;
    prixUnitaire?: number;
    montant?: number;
    poids?: number;
    produitId?: number;
    achatLigneProformaId?: number;
}

export class AchatArticleLigneProforma implements IAchatArticleLigneProforma {
    constructor(
        public id?: number,
        public idArticleLigneProforma?: number,
        public numeroSequence?: number,
        public nombreCaissesTc?: number,
        public nombreFeuillesCaisse?: number,
        public dimention?: number,
        public quantite?: number,
        public prixUnitaire?: number,
        public montant?: number,
        public poids?: number,
        public produitId?: number,
        public achatLigneProformaId?: number
    ) {}
}
