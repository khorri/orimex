import { Moment } from 'moment';
import { IStockArticleConteneurReception } from 'app/shared/model//stock-article-conteneur-reception.model';

export interface IStockConteneurReception {
    id?: number;
    idOperation?: number;
    dateReception?: Moment;
    numeroOperation?: string;
    numeroBonEntree?: string;
    numeroConstatNonConformite?: string;
    isValide?: number;
    stockReceptionId?: number;
    stockArticleConteneurReceptions?: IStockArticleConteneurReception[];
}

export class StockConteneurReception implements IStockConteneurReception {
    constructor(
        public id?: number,
        public idOperation?: number,
        public dateReception?: Moment,
        public numeroOperation?: string,
        public numeroBonEntree?: string,
        public numeroConstatNonConformite?: string,
        public isValide?: number,
        public stockReceptionId?: number,
        public stockArticleConteneurReceptions?: IStockArticleConteneurReception[]
    ) {}
}
