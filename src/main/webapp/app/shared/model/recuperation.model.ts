import { Moment } from 'moment';

export interface IRecuperation {
    id?: number;
    idOperation?: number;
    dateOperation?: Moment;
    nombrePanneaux?: number;
    numeroOperation?: string;
    superficie?: number;
    produitId?: number;
    depotId?: number;
    utilisateurId?: number;
}

export class Recuperation implements IRecuperation {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateOperation?: Moment,
        public nombrePanneaux?: number,
        public numeroOperation?: string,
        public superficie?: number,
        public produitId?: number,
        public depotId?: number,
        public utilisateurId?: number
    ) {}
}
