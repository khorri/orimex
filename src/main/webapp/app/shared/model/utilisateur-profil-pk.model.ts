export interface IUtilisateurProfilPK {
    id?: number;
    utilisateurId?: number;
    profilId?: number;
}

export class UtilisateurProfilPK implements IUtilisateurProfilPK {
    constructor(public id?: number, public utilisateurId?: number, public profilId?: number) {}
}
