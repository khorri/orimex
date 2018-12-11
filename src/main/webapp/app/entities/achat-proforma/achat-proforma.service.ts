import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatProforma } from 'app/shared/model/achat-proforma.model';

type EntityResponseType = HttpResponse<IAchatProforma>;
type EntityArrayResponseType = HttpResponse<IAchatProforma[]>;

@Injectable({ providedIn: 'root' })
export class AchatProformaService {
    public resourceUrl = SERVER_API_URL + 'api/achat-proformas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-proformas';

    constructor(private http: HttpClient) {}

    create(achatProforma: IAchatProforma): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatProforma);
        return this.http
            .post<IAchatProforma>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(achatProforma: IAchatProforma): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatProforma);
        return this.http
            .put<IAchatProforma>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAchatProforma>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatProforma[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatProforma[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(achatProforma: IAchatProforma): IAchatProforma {
        const copy: IAchatProforma = Object.assign({}, achatProforma, {
            dateProforma:
                achatProforma.dateProforma != null && achatProforma.dateProforma.isValid()
                    ? achatProforma.dateProforma.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateProforma = res.body.dateProforma != null ? moment(res.body.dateProforma) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((achatProforma: IAchatProforma) => {
                achatProforma.dateProforma = achatProforma.dateProforma != null ? moment(achatProforma.dateProforma) : null;
            });
        }
        return res;
    }
}
