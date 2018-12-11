export interface IProfilAction {
    id?: number;
    actionId?: number;
    profilId?: number;
}

export class ProfilAction implements IProfilAction {
    constructor(public id?: number, public actionId?: number, public profilId?: number) {}
}
