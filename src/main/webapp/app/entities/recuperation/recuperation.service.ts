import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IRecuperation } from 'app/shared/model/recuperation.model';

type EntityResponseType = HttpResponse<IRecuperation>;
type EntityArrayResponseType = HttpResponse<IRecuperation[]>;

@Injectable({ providedIn: 'root' })
export class RecuperationService {
    public resourceUrl = SERVER_API_URL + 'api/recuperations';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/recuperations';

    constructor(private http: HttpClient) {}

    create(recuperation: IRecuperation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(recuperation);
        return this.http
            .post<IRecuperation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(recuperation: IRecuperation): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(recuperation);
        return this.http
            .put<IRecuperation>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IRecuperation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRecuperation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IRecuperation[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(recuperation: IRecuperation): IRecuperation {
        const copy: IRecuperation = Object.assign({}, recuperation, {
            dateOperation:
                recuperation.dateOperation != null && recuperation.dateOperation.isValid() ? recuperation.dateOperation.toJSON() : null
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
            res.body.forEach((recuperation: IRecuperation) => {
                recuperation.dateOperation = recuperation.dateOperation != null ? moment(recuperation.dateOperation) : null;
            });
        }
        return res;
    }
}
