export interface IAchatArticleConteneurReception {
    id?: number;
    idArticleConteneurReception?: number;
    dimension?: number;
    nombreCaissestc?: number;
    nombreFeuillecaisse?: number;
    achatConteneurReceptionId?: number;
    produitId?: number;
}

export class AchatArticleConteneurReception implements IAchatArticleConteneurReception {
    constructor(
        public id?: number,
        public idArticleConteneurReception?: number,
        public dimension?: number,
        public nombreCaissestc?: number,
        public nombreFeuillecaisse?: number,
        public achatConteneurReceptionId?: number,
        public produitId?: number
    ) {}
}
