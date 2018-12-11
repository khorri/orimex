import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IReception } from 'app/shared/model/reception.model';

type EntityResponseType = HttpResponse<IReception>;
type EntityArrayResponseType = HttpResponse<IReception[]>;

@Injectable({ providedIn: 'root' })
export class ReceptionService {
    public resourceUrl = SERVER_API_URL + 'api/receptions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/receptions';

    constructor(private http: HttpClient) {}

    create(reception: IReception): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reception);
        return this.http
            .post<IReception>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(reception: IReception): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(reception);
        return this.http
            .put<IReception>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IReception>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReception[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IReception[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(reception: IReception): IReception {
        const copy: IReception = Object.assign({}, reception, {
            dateReception: reception.dateReception != null && reception.dateReception.isValid() ? reception.dateReception.toJSON() : null
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
            res.body.forEach((reception: IReception) => {
                reception.dateReception = reception.dateReception != null ? moment(reception.dateReception) : null;
            });
        }
        return res;
    }
}
