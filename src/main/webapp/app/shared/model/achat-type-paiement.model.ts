export interface IAchatTypePaiement {
    id?: number;
    idTypePaiement?: number;
    libelleTypePaiement?: string;
}

export class AchatTypePaiement implements IAchatTypePaiement {
    constructor(public id?: number, public idTypePaiement?: number, public libelleTypePaiement?: string) {}
}
