import { Moment } from 'moment';
import { IAchatPrevisionArrivage } from 'app/shared/model//achat-prevision-arrivage.model';

export interface IAchatDossier {
    id?: number;
    idDossier?: number;
    numeroDossier?: string;
    codeFournisseur?: string;
    designationFournisseur?: string;
    incoterm?: string;
    reference?: string;
    tolerance?: number;
    numeroEi?: string;
    numeroEiComp?: string;
    dateCreation?: Moment;
    delaiPaiement?: number;
    delaiValiditeLc?: number;
    dateExpedition?: Moment;
    dateOuverture?: Moment;
    dateValiditeEi?: Moment;
    dateLimiteExp?: Moment;
    dateValiditeLc?: Moment;
    montnatTotal?: number;
    montnatFob?: number;
    montnatFret?: number;
    totalTc?: number;
    designationBanque?: string;
    paiementAvue?: number;
    encours?: number;
    achatBanqueId?: number;
    typePaiementId?: number;
    achatDeviseId?: number;
    achatPrevisionArrivages?: IAchatPrevisionArrivage[];
    achatStatutDossierId?: number;
}

export class AchatDossier implements IAchatDossier {
    constructor(
        public id?: number,
        public idDossier?: number,
        public numeroDossier?: string,
        public codeFournisseur?: string,
        public designationFournisseur?: string,
        public incoterm?: string,
        public reference?: string,
        public tolerance?: number,
        public numeroEi?: string,
        public numeroEiComp?: string,
        public dateCreation?: Moment,
        public delaiPaiement?: number,
        public delaiValiditeLc?: number,
        public dateExpedition?: Moment,
        public dateOuverture?: Moment,
        public dateValiditeEi?: Moment,
        public dateLimiteExp?: Moment,
        public dateValiditeLc?: Moment,
        public montnatTotal?: number,
        public montnatFob?: number,
        public montnatFret?: number,
        public totalTc?: number,
        public designationBanque?: string,
        public paiementAvue?: number,
        public encours?: number,
        public achatBanqueId?: number,
        public typePaiementId?: number,
        public achatDeviseId?: number,
        public achatPrevisionArrivages?: IAchatPrevisionArrivage[],
        public achatStatutDossierId?: number
    ) {}
}
