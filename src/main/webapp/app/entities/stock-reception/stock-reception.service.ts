import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStockReception } from 'app/shared/model/stock-reception.model';

type EntityResponseType = HttpResponse<IStockReception>;
type EntityArrayResponseType = HttpResponse<IStockReception[]>;

@Injectable({ providedIn: 'root' })
export class StockReceptionService {
    public resourceUrl = SERVER_API_URL + 'api/stock-receptions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/stock-receptions';

    constructor(private http: HttpClient) {}

    create(stockReception: IStockReception): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(stockReception);
        return this.http
            .post<IStockReception>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(stockReception: IStockReception): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(stockReception);
        return this.http
            .put<IStockReception>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStockReception>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStockReception[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStockReception[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(stockReception: IStockReception): IStockReception {
        const copy: IStockReception = Object.assign({}, stockReception, {
            dateReception:
                stockReception.dateReception != null && stockReception.dateReception.isValid()
                    ? stockReception.dateReception.format(DATE_FORMAT)
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
            res.body.forEach((stockReception: IStockReception) => {
                stockReception.dateReception = stockReception.dateReception != null ? moment(stockReception.dateReception) : null;
            });
        }
        return res;
    }
}
