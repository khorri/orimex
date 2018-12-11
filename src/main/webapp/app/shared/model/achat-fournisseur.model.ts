export interface IAchatFournisseur {
    id?: number;
    typeFournisseur?: string;
    codeFournisseur?: string;
    designationFournisseur?: string;
}

export class AchatFournisseur implements IAchatFournisseur {
    constructor(
        public id?: number,
        public typeFournisseur?: string,
        public codeFournisseur?: string,
        public designationFournisseur?: string
    ) {}
}
