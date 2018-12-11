export interface IDepot {
    id?: number;
    idDepot?: number;
    referenceDepot?: string;
    utilisateurDepots?: string;
    villeId?: number;
}

export class Depot implements IDepot {
    constructor(
        public id?: number,
        public idDepot?: number,
        public referenceDepot?: string,
        public utilisateurDepots?: string,
        public villeId?: number
    ) {}
}
