export interface ICaisse {
    id?: number;
    idCaisse?: number;
    nombrePlateaux?: number;
    numeroConteneur?: string;
    referenceCaisse?: string;
}

export class Caisse implements ICaisse {
    constructor(
        public id?: number,
        public idCaisse?: number,
        public nombrePlateaux?: number,
        public numeroConteneur?: string,
        public referenceCaisse?: string
    ) {}
}
