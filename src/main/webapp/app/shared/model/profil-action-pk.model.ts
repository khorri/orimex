export interface IProfilActionPK {
    id?: number;
    actionId?: number;
    profilId?: number;
}

export class ProfilActionPK implements IProfilActionPK {
    constructor(public id?: number, public actionId?: number, public profilId?: number) {}
}
