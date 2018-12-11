import { IDepot } from 'app/shared/model//depot.model';

export interface IVille {
    id?: number;
    idVille?: number;
    libelleVille?: string;
    depots?: IDepot[];
}

export class Ville implements IVille {
    constructor(public id?: number, public idVille?: number, public libelleVille?: string, public depots?: IDepot[]) {}
}
