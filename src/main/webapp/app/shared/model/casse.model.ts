import { Moment } from 'moment';

export interface ICasse {
    id?: number;
    idOperation?: number;
    dateOperation?: Moment;
    description?: string;
    nombrePlateaux?: number;
    numeroOperation?: string;
    superfcie?: number;
    utilisateurId?: number;
    caisseId?: number;
}

export class Casse implements ICasse {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateOperation?: Moment,
        public description?: string,
        public nombrePlateaux?: number,
        public numeroOperation?: string,
        public superfcie?: number,
        public utilisateurId?: number,
        public caisseId?: number
    ) {}
}
