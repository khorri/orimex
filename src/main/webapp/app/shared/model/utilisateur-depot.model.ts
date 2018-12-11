export interface IUtilisateurDepot {
    id?: number;
    utilisateurId?: number;
}

export class UtilisateurDepot implements IUtilisateurDepot {
    constructor(public id?: number, public utilisateurId?: number) {}
}
