import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRetour } from 'app/shared/model/retour.model';

type EntityResponseType = HttpResponse<IRetour>;
type EntityArrayResponseType = HttpResponse<IRetour[]>;

@Injectable({ providedIn: 'root' })
export class RetourService {
    public resourceUrl = SERVER_API_URL + 'api/retours';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/retours';

    constructor(private http: HttpClient) {}

    create(retour: IRetour): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(retour);
        return this.http
            .post<IRetour>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(retour: IRetour): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(retour);
        return this.http
            .put<IRetour>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRetour>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRetour[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRetour[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(retour: IRetour): IRetour {
        const copy: IRetour = Object.assign({}, retour, {
            dateOperation: retour.dateOperation != null && retour.dateOperation.isValid() ? retour.dateOperation.toJSON() : null
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
            res.body.forEach((retour: IRetour) => {
                retour.dateOperation = retour.dateOperation != null ? moment(retour.dateOperation) : null;
            });
        }
        return res;
    }
}
