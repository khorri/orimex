import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';

type EntityResponseType = HttpResponse<IAchatPrevisionArrivage>;
type EntityArrayResponseType = HttpResponse<IAchatPrevisionArrivage[]>;

@Injectable({ providedIn: 'root' })
export class AchatPrevisionArrivageService {
    public resourceUrl = SERVER_API_URL + 'api/achat-prevision-arrivages';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-prevision-arrivages';

    constructor(private http: HttpClient) {}

    create(achatPrevisionArrivage: IAchatPrevisionArrivage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatPrevisionArrivage);
        return this.http
            .post<IAchatPrevisionArrivage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(achatPrevisionArrivage: IAchatPrevisionArrivage): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatPrevisionArrivage);
        return this.http
            .put<IAchatPrevisionArrivage>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAchatPrevisionArrivage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatPrevisionArrivage[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatPrevisionArrivage[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(achatPrevisionArrivage: IAchatPrevisionArrivage): IAchatPrevisionArrivage {
        const copy: IAchatPrevisionArrivage = Object.assign({}, achatPrevisionArrivage, {
            etd:
                achatPrevisionArrivage.etd != null && achatPrevisionArrivage.etd.isValid()
                    ? achatPrevisionArrivage.etd.format(DATE_FORMAT)
                    : null,
            eta:
                achatPrevisionArrivage.eta != null && achatPrevisionArrivage.eta.isValid()
                    ? achatPrevisionArrivage.eta.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.etd = res.body.etd != null ? moment(res.body.etd) : null;
            res.body.eta = res.body.eta != null ? moment(res.body.eta) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((achatPrevisionArrivage: IAchatPrevisionArrivage) => {
                achatPrevisionArrivage.etd = achatPrevisionArrivage.etd != null ? moment(achatPrevisionArrivage.etd) : null;
                achatPrevisionArrivage.eta = achatPrevisionArrivage.eta != null ? moment(achatPrevisionArrivage.eta) : null;
            });
        }
        return res;
    }
}
