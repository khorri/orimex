import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBon } from 'app/shared/model/bon.model';

type EntityResponseType = HttpResponse<IBon>;
type EntityArrayResponseType = HttpResponse<IBon[]>;

@Injectable({ providedIn: 'root' })
export class BonService {
    public resourceUrl = SERVER_API_URL + 'api/bons';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/bons';

    constructor(private http: HttpClient) {}

    create(bon: IBon): Observable<EntityResponseType> {
        return this.http.post<IBon>(this.resourceUrl, bon, { observe: 'response' });
    }

    update(bon: IBon): Observable<EntityResponseType> {
        return this.http.put<IBon>(this.resourceUrl, bon, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IBon>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBon[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IBon[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
