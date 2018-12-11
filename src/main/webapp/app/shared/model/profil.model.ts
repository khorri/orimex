import { IUtilisateurProfil } from 'app/shared/model//utilisateur-profil.model';
import { IProfilAction } from 'app/shared/model//profil-action.model';

export interface IProfil {
    id?: number;
    codeProfil?: string;
    descriptionProfil?: string;
    utilisateurProfils?: IUtilisateurProfil[];
    profilActions?: IProfilAction[];
}

export class Profil implements IProfil {
    constructor(
        public id?: number,
        public codeProfil?: string,
        public descriptionProfil?: string,
        public utilisateurProfils?: IUtilisateurProfil[],
        public profilActions?: IProfilAction[]
    ) {}
}
