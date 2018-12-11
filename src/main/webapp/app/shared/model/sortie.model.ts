import { Moment } from 'moment';

export interface ISortie {
    id?: number;
    idOperation?: number;
    dateOperation?: Moment;
    nombrePlateaux?: number;
    numeroOperation?: string;
    utilisateurId?: number;
    bonId?: number;
    caisseId?: number;
    depotId?: number;
}

export class Sortie implements ISortie {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateOperation?: Moment,
        public nombrePlateaux?: number,
        public numeroOperation?: string,
        public utilisateurId?: number,
        public bonId?: number,
        public caisseId?: number,
        public depotId?: number
    ) {}
}
