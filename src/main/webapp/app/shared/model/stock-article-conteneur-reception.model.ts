export interface IStockArticleConteneurReception {
    id?: number;
    idArticleConteneurReception?: number;
    dimension?: number;
    nombreCaissestc?: number;
    nombreFeuillecaisse?: number;
    isNonConfrome?: number;
    achatArticleConteneurArrivageId?: number;
    stockConteneurReceptionId?: number;
}

export class StockArticleConteneurReception implements IStockArticleConteneurReception {
    constructor(
        public id?: number,
        public idArticleConteneurReception?: number,
        public dimension?: number,
        public nombreCaissestc?: number,
        public nombreFeuillecaisse?: number,
        public isNonConfrome?: number,
        public achatArticleConteneurArrivageId?: number,
        public stockConteneurReceptionId?: number
    ) {}
}
