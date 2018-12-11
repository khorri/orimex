import { IAchatDossier } from 'app/shared/model//achat-dossier.model';

export interface IAchatStatutDossier {
    id?: number;
    idStatutDossier?: number;
    libelleStatutDossier?: string;
    achatDossiers?: IAchatDossier[];
}

export class AchatStatutDossier implements IAchatStatutDossier {
    constructor(
        public id?: number,
        public idStatutDossier?: number,
        public libelleStatutDossier?: string,
        public achatDossiers?: IAchatDossier[]
    ) {}
}
