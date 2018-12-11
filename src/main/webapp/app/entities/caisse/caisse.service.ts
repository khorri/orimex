import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICaisse } from 'app/shared/model/caisse.model';

type EntityResponseType = HttpResponse<ICaisse>;
type EntityArrayResponseType = HttpResponse<ICaisse[]>;

@Injectable({ providedIn: 'root' })
export class CaisseService {
    public resourceUrl = SERVER_API_URL + 'api/caisses';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/caisses';

    constructor(private http: HttpClient) {}

    create(caisse: ICaisse): Observable<EntityResponseType> {
        return this.http.post<ICaisse>(this.resourceUrl, caisse, { observe: 'response' });
    }

    update(caisse: ICaisse): Observable<EntityResponseType> {
        return this.http.put<ICaisse>(this.resourceUrl, caisse, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICaisse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICaisse[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICaisse[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
