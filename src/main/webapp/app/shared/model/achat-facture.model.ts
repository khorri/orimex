import { Moment } from 'moment';
import { IAchatConteneurArrivage } from 'app/shared/model//achat-conteneur-arrivage.model';

export interface IAchatFacture {
    id?: number;
    idFacture?: number;
    numeroFacture?: string;
    dateFacture?: Moment;
    montantFob?: number;
    montantFret?: number;
    montantTotal?: number;
    nombreTc?: number;
    poids?: number;
    quantite?: number;
    ajustement?: number;
    numeroPiece?: string;
    dateBl?: Moment;
    numeroBl?: string;
    dateEcheance?: Moment;
    etat?: number;
    banqueReglement?: number;
    dateValeur?: Moment;
    cours?: number;
    montantDh?: number;
    echecanceFinancement?: Moment;
    interet1?: number;
    dateReglement?: Moment;
    derniereEcheance?: Moment;
    transmise?: number;
    echeanceRefinancement?: Moment;
    interet2?: number;
    interet1Regle?: number;
    achatArrivageId?: number;
    achatConteneurArrivages?: IAchatConteneurArrivage[];
}

export class AchatFacture implements IAchatFacture {
    constructor(
        public id?: number,
        public idFacture?: number,
        public numeroFacture?: string,
        public dateFacture?: Moment,
        public montantFob?: number,
        public montantFret?: number,
        public montantTotal?: number,
        public nombreTc?: number,
        public poids?: number,
        public quantite?: number,
        public ajustement?: number,
        public numeroPiece?: string,
        public dateBl?: Moment,
        public numeroBl?: string,
        public dateEcheance?: Moment,
        public etat?: number,
        public banqueReglement?: number,
        public dateValeur?: Moment,
        public cours?: number,
        public montantDh?: number,
        public echecanceFinancement?: Moment,
        public interet1?: number,
        public dateReglement?: Moment,
        public derniereEcheance?: Moment,
        public transmise?: number,
        public echeanceRefinancement?: Moment,
        public interet2?: number,
        public interet1Regle?: number,
        public achatArrivageId?: number,
        public achatConteneurArrivages?: IAchatConteneurArrivage[]
    ) {}
}
