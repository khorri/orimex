export interface IFamilleProduit {
    id?: number;
    idFamille?: number;
    designationFamille?: string;
}

export class FamilleProduit implements IFamilleProduit {
    constructor(public id?: number, public idFamille?: number, public designationFamille?: string) {}
}
