import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';

type EntityResponseType = HttpResponse<IStockArticleConteneurReception>;
type EntityArrayResponseType = HttpResponse<IStockArticleConteneurReception[]>;

@Injectable({ providedIn: 'root' })
export class StockArticleConteneurReceptionService {
    public resourceUrl = SERVER_API_URL + 'api/stock-article-conteneur-receptions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/stock-article-conteneur-receptions';

    constructor(private http: HttpClient) {}

    create(stockArticleConteneurReception: IStockArticleConteneurReception): Observable<EntityResponseType> {
        return this.http.post<IStockArticleConteneurReception>(this.resourceUrl, stockArticleConteneurReception, { observe: 'response' });
    }

    update(stockArticleConteneurReception: IStockArticleConteneurReception): Observable<EntityResponseType> {
        return this.http.put<IStockArticleConteneurReception>(this.resourceUrl, stockArticleConteneurReception, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IStockArticleConteneurReception>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStockArticleConteneurReception[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IStockArticleConteneurReception[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
