export interface IOrigine {
    id?: number;
    idOrigine?: number;
    designationOrigine?: string;
}

export class Origine implements IOrigine {
    constructor(public id?: number, public idOrigine?: number, public designationOrigine?: string) {}
}
