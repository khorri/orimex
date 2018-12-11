export interface IConteneur {
    id?: number;
    numeroConteneur?: string;
}

export class Conteneur implements IConteneur {
    constructor(public id?: number, public numeroConteneur?: string) {}
}
