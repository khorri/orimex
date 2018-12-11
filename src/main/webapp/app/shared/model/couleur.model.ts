export interface ICouleur {
    id?: number;
    idCouleur?: number;
    codeHtml?: string;
}

export class Couleur implements ICouleur {
    constructor(public id?: number, public idCouleur?: number, public codeHtml?: string) {}
}
