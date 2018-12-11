import { Moment } from 'moment';
import { IAchatFacture } from 'app/shared/model//achat-facture.model';

export interface IAchatArrivage {
    id?: number;
    idArrivage?: number;
    numeroDossierArrivage?: string;
    codeCompagnieMaritime?: string;
    codeOperateur?: string;
    codeTransitaire?: string;
    codeTransporteur?: string;
    dateArrivePort?: Moment;
    designationCompagnieMaritime?: string;
    designationOperateur?: string;
    designationTransitaire?: string;
    designationTransporteur?: string;
    flagProduit?: string;
    franchise?: number;
    montantFob?: number;
    montantFret?: number;
    montantTotal?: number;
    nombreTc?: number;
    dateRealisation?: Moment;
    poid?: number;
    achatFactures?: IAchatFacture[];
    achatDossierId?: number;
}

export class AchatArrivage implements IAchatArrivage {
    constructor(
        public id?: number,
        public idArrivage?: number,
        public numeroDossierArrivage?: string,
        public codeCompagnieMaritime?: string,
        public codeOperateur?: string,
        public codeTransitaire?: string,
        public codeTransporteur?: string,
        public dateArrivePort?: Moment,
        public designationCompagnieMaritime?: string,
        public designationOperateur?: string,
        public designationTransitaire?: string,
        public designationTransporteur?: string,
        public flagProduit?: string,
        public franchise?: number,
        public montantFob?: number,
        public montantFret?: number,
        public montantTotal?: number,
        public nombreTc?: number,
        public dateRealisation?: Moment,
        public poid?: number,
        public achatFactures?: IAchatFacture[],
        public achatDossierId?: number
    ) {}
}
