export interface IAchatConteneurReception {
    id?: number;
    idConteneurReception?: number;
    numeroConteneur?: string;
    numeroSequence?: number;
    achatConteneurArrivageId?: number;
}

export class AchatConteneurReception implements IAchatConteneurReception {
    constructor(
        public id?: number,
        public idConteneurReception?: number,
        public numeroConteneur?: string,
        public numeroSequence?: number,
        public achatConteneurArrivageId?: number
    ) {}
}
