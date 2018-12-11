import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IJourFerier } from 'app/shared/model/jour-ferier.model';

type EntityResponseType = HttpResponse<IJourFerier>;
type EntityArrayResponseType = HttpResponse<IJourFerier[]>;

@Injectable({ providedIn: 'root' })
export class JourFerierService {
    public resourceUrl = SERVER_API_URL + 'api/jour-feriers';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/jour-feriers';

    constructor(private http: HttpClient) {}

    create(jourFerier: IJourFerier): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jourFerier);
        return this.http
            .post<IJourFerier>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(jourFerier: IJourFerier): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(jourFerier);
        return this.http
            .put<IJourFerier>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IJourFerier>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJourFerier[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IJourFerier[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(jourFerier: IJourFerier): IJourFerier {
        const copy: IJourFerier = Object.assign({}, jourFerier, {
            debut: jourFerier.debut != null && jourFerier.debut.isValid() ? jourFerier.debut.format(DATE_FORMAT) : null,
            fin: jourFerier.fin != null && jourFerier.fin.isValid() ? jourFerier.fin.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.debut = res.body.debut != null ? moment(res.body.debut) : null;
            res.body.fin = res.body.fin != null ? moment(res.body.fin) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((jourFerier: IJourFerier) => {
                jourFerier.debut = jourFerier.debut != null ? moment(jourFerier.debut) : null;
                jourFerier.fin = jourFerier.fin != null ? moment(jourFerier.fin) : null;
            });
        }
        return res;
    }
}
