export interface ICamion {
    id?: number;
    idCamion?: number;
    numeroCamion?: string;
}

export class Camion implements ICamion {
    constructor(public id?: number, public idCamion?: number, public numeroCamion?: string) {}
}
