import { Moment } from 'moment';
import { IAchatLigneProforma } from 'app/shared/model//achat-ligne-proforma.model';

export interface IAchatProforma {
    id?: number;
    idProforma?: number;
    nombreTc?: number;
    coutFob?: number;
    coutFret?: number;
    montantTotal?: number;
    numeroBonProforma?: string;
    typeAcht?: string;
    poids?: number;
    dateProforma?: Moment;
    achatLigneProformas?: IAchatLigneProforma[];
    achatDossierId?: number;
}

export class AchatProforma implements IAchatProforma {
    constructor(
        public id?: number,
        public idProforma?: number,
        public nombreTc?: number,
        public coutFob?: number,
        public coutFret?: number,
        public montantTotal?: number,
        public numeroBonProforma?: string,
        public typeAcht?: string,
        public poids?: number,
        public dateProforma?: Moment,
        public achatLigneProformas?: IAchatLigneProforma[],
        public achatDossierId?: number
    ) {}
}
