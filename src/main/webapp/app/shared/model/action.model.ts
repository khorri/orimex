import { IProfilAction } from 'app/shared/model//profil-action.model';

export interface IAction {
    id?: number;
    codeAction?: string;
    libelleAction?: string;
    menuId?: number;
    profilActions?: IProfilAction[];
}

export class Action implements IAction {
    constructor(
        public id?: number,
        public codeAction?: string,
        public libelleAction?: string,
        public menuId?: number,
        public profilActions?: IProfilAction[]
    ) {}
}
