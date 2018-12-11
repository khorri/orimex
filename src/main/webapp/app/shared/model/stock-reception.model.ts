import { Moment } from 'moment';

export interface IStockReception {
    id?: number;
    idOperation?: number;
    dateReception?: Moment;
    numeroOperation?: string;
    numeroBonEntree?: string;
    numeroConstatNonConformite?: string;
    isValide?: number;
    utilisateurId?: number;
}

export class StockReception implements IStockReception {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateReception?: Moment,
        public numeroOperation?: string,
        public numeroBonEntree?: string,
        public numeroConstatNonConformite?: string,
        public isValide?: number,
        public utilisateurId?: number
    ) {}
}
