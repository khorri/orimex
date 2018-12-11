export interface IUtilisateurProfil {
    id?: number;
    utilisateurId?: number;
    profilId?: number;
}

export class UtilisateurProfil implements IUtilisateurProfil {
    constructor(public id?: number, public utilisateurId?: number, public profilId?: number) {}
}
