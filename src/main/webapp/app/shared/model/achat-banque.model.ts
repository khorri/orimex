import { IAchatDossier } from 'app/shared/model//achat-dossier.model';

export interface IAchatBanque {
    id?: number;
    idBanque?: number;
    codeBanque?: string;
    designationBanque?: string;
    achatDossiers?: IAchatDossier[];
}

export class AchatBanque implements IAchatBanque {
    constructor(
        public id?: number,
        public idBanque?: number,
        public codeBanque?: string,
        public designationBanque?: string,
        public achatDossiers?: IAchatDossier[]
    ) {}
}
