import { Moment } from 'moment';

export interface ITransfert {
    id?: number;
    idOperation?: number;
    dateOperation?: Moment;
    nombrePlateaux?: number;
    numeroOperation?: string;
    utilisateurId?: number;
    bonId?: number;
    caisseId?: number;
    camionId?: number;
    conteneurId?: number;
    depotId?: number;
}

export class Transfert implements ITransfert {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateOperation?: Moment,
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
