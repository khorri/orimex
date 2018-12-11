export interface IAchatLigneProforma {
    id?: number;
    idLigneProforma?: number;
    nombreConteneurs?: number;
    montant?: number;
    numeroSequence?: number;
    poids?: number;
    achatProformaId?: number;
}

export class AchatLigneProforma implements IAchatLigneProforma {
    constructor(
        public id?: number,
        public idLigneProforma?: number,
        public nombreConteneurs?: number,
        public montant?: number,
        public numeroSequence?: number,
        public poids?: number,
        public achatProformaId?: number
    ) {}
}
