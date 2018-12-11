import { IRetour } from 'app/shared/model//retour.model';

export interface IBon {
    id?: number;
    idBon?: number;
    idTypeBon?: number;
    numeroBon?: string;
    retours?: IRetour[];
}

export class Bon implements IBon {
    constructor(
        public id?: number,
        public idBon?: number,
        public idTypeBon?: number,
        public numeroBon?: string,
        public retours?: IRetour[]
    ) {}
}
