import { Moment } from 'moment';

export interface IReception {
    id?: number;
    idOperation?: number;
    dateReception?: Moment;
    nombrePlateaux?: number;
    numeroOperation?: string;
    utilisateurId?: number;
    bonId?: number;
    caisseId?: number;
    camionId?: number;
    conteneurId?: number;
    depotId?: number;
}

export class Reception implements IReception {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateReception?: Moment,
        public nombrePlateaux?: number,
        public numeroOperation?: string,
        public utilisateurId?: number,
        public bonId?: number,
        public caisseId?: number,
        public camionId?: number,
        public conteneurId?: number,
        public depotId?: number
    ) {}
}
