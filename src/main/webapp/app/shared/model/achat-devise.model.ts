import { IAchatDossier } from 'app/shared/model//achat-dossier.model';

export interface IAchatDevise {
    id?: number;
    idDevise?: number;
    codeDevise?: string;
    libelleDevise?: string;
    achatDossiers?: IAchatDossier[];
}

export class AchatDevise implements IAchatDevise {
    constructor(
        public id?: number,
        public idDevise?: number,
        public codeDevise?: string,
        public libelleDevise?: string,
        public achatDossiers?: IAchatDossier[]
    ) {}
}
