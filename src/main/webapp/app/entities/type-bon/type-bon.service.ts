import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITypeBon } from 'app/shared/model/type-bon.model';

type EntityResponseType = HttpResponse<ITypeBon>;
type EntityArrayResponseType = HttpResponse<ITypeBon[]>;

@Injectable({ providedIn: 'root' })
export class TypeBonService {
    public resourceUrl = SERVER_API_URL + 'api/type-bons';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/type-bons';

    constructor(private http: HttpClient) {}

    create(typeBon: ITypeBon): Observable<EntityResponseType> {
        return this.http.post<ITypeBon>(this.resourceUrl, typeBon, { observe: 'response' });
    }

    update(typeBon: ITypeBon): Observable<EntityResponseType> {
        return this.http.put<ITypeBon>(this.resourceUrl, typeBon, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITypeBon>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeBon[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITypeBon[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
