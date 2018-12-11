import { Moment } from 'moment';

export interface IRetour {
    id?: number;
    idOperation?: number;
    dateOperation?: Moment;
    nombrePlateaux?: number;
    numeroOperation?: string;
    superfecie?: number;
    produitId?: number;
    utilisateurId?: number;
    bonId?: number;
}

export class Retour implements IRetour {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateOperation?: Moment,
        public nombrePlateaux?: number,
        public numeroOperation?: string,
        public superfecie?: number,
        public produitId?: number,
        public utilisateurId?: number,
        public bonId?: number
    ) {}
}
