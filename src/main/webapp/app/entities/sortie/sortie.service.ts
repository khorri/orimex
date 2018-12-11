import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISortie } from 'app/shared/model/sortie.model';

type EntityResponseType = HttpResponse<ISortie>;
type EntityArrayResponseType = HttpResponse<ISortie[]>;

@Injectable({ providedIn: 'root' })
export class SortieService {
    public resourceUrl = SERVER_API_URL + 'api/sorties';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/sorties';

    constructor(private http: HttpClient) {}

    create(sortie: ISortie): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sortie);
        return this.http
            .post<ISortie>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(sortie: ISortie): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sortie);
        return this.http
            .put<ISortie>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISortie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISortie[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISortie[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(sortie: ISortie): ISortie {
        const copy: ISortie = Object.assign({}, sortie, {
            dateOperation: sortie.dateOperation != null && sortie.dateOperation.isValid() ? sortie.dateOperation.toJSON() : null
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
            res.body.forEach((sortie: ISortie) => {
                sortie.dateOperation = sortie.dateOperation != null ? moment(sortie.dateOperation) : null;
            });
        }
        return res;
    }
}
