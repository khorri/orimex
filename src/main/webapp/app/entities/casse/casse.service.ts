import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICasse } from 'app/shared/model/casse.model';

type EntityResponseType = HttpResponse<ICasse>;
type EntityArrayResponseType = HttpResponse<ICasse[]>;

@Injectable({ providedIn: 'root' })
export class CasseService {
    public resourceUrl = SERVER_API_URL + 'api/casses';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/casses';

    constructor(private http: HttpClient) {}

    create(casse: ICasse): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(casse);
        return this.http
            .post<ICasse>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(casse: ICasse): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(casse);
        return this.http
            .put<ICasse>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICasse>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICasse[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICasse[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(casse: ICasse): ICasse {
        const copy: ICasse = Object.assign({}, casse, {
            dateOperation: casse.dateOperation != null && casse.dateOperation.isValid() ? casse.dateOperation.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateOperation = res.body.dateOperation != null ? moment(res.body.dateOperation) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((casse: ICasse) => {
                casse.dateOperation = casse.dateOperation != null ? moment(casse.dateOperation) : null;
            });
        }
        return res;
    }
}
