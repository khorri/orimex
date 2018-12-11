import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';

type EntityResponseType = HttpResponse<IStockConteneurReception>;
type EntityArrayResponseType = HttpResponse<IStockConteneurReception[]>;

@Injectable({ providedIn: 'root' })
export class StockConteneurReceptionService {
    public resourceUrl = SERVER_API_URL + 'api/stock-conteneur-receptions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/stock-conteneur-receptions';

    constructor(private http: HttpClient) {}

    create(stockConteneurReception: IStockConteneurReception): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(stockConteneurReception);
        return this.http
            .post<IStockConteneurReception>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(stockConteneurReception: IStockConteneurReception): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(stockConteneurReception);
        return this.http
            .put<IStockConteneurReception>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStockConteneurReception>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStockConteneurReception[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStockConteneurReception[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(stockConteneurReception: IStockConteneurReception): IStockConteneurReception {
        const copy: IStockConteneurReception = Object.assign({}, stockConteneurReception, {
            dateReception:
                stockConteneurReception.dateReception != null && stockConteneurReception.dateReception.isValid()
                    ? stockConteneurReception.dateReception.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateReception = res.body.dateReception != null ? moment(res.body.dateReception) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((stockConteneurReception: IStockConteneurReception) => {
                stockConteneurReception.dateReception =
                    stockConteneurReception.dateReception != null ? moment(stockConteneurReception.dateReception) : null;
            });
        }
        return res;
    }
}
