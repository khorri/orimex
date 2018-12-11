export interface ITypeBon {
    id?: number;
    idTypeBon?: number;
    libelleTypeBon?: string;
}

export class TypeBon implements ITypeBon {
    constructor(public id?: number, public idTypeBon?: number, public libelleTypeBon?: string) {}
}
