import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';

type EntityResponseType = HttpResponse<IAchatArrivage>;
type EntityArrayResponseType = HttpResponse<IAchatArrivage[]>;

@Injectable({ providedIn: 'root' })
export class AchatArrivageService {
    public resourceUrl = SERVER_API_URL + 'api/achat-arrivages';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-arrivages';

    constructor(private http: HttpClient) {}

    create(achatArrivage: IAchatArrivage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatArrivage);
        return this.http
            .post<IAchatArrivage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(achatArrivage: IAchatArrivage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatArrivage);
        return this.http
            .put<IAchatArrivage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAchatArrivage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatArrivage[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatArrivage[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(achatArrivage: IAchatArrivage): IAchatArrivage {
        const copy: IAchatArrivage = Object.assign({}, achatArrivage, {
            dateArrivePort:
                achatArrivage.dateArrivePort != null && achatArrivage.dateArrivePort.isValid()
                    ? achatArrivage.dateArrivePort.format(DATE_FORMAT)
                    : null,
            dateRealisation:
                achatArrivage.dateRealisation != null && achatArrivage.dateRealisation.isValid()
                    ? achatArrivage.dateRealisation.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateArrivePort = res.body.dateArrivePort != null ? moment(res.body.dateArrivePort) : null;
            res.body.dateRealisation = res.body.dateRealisation != null ? moment(res.body.dateRealisation) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((achatArrivage: IAchatArrivage) => {
                achatArrivage.dateArrivePort = achatArrivage.dateArrivePort != null ? moment(achatArrivage.dateArrivePort) : null;
                achatArrivage.dateRealisation = achatArrivage.dateRealisation != null ? moment(achatArrivage.dateRealisation) : null;
            });
        }
        return res;
    }
}
