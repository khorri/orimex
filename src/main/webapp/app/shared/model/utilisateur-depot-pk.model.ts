export interface IUtilisateurDepotPK {
    id?: number;
    utilisateurId?: number;
    depotId?: number;
}

export class UtilisateurDepotPK implements IUtilisateurDepotPK {
    constructor(public id?: number, public utilisateurId?: number, public depotId?: number) {}
}
