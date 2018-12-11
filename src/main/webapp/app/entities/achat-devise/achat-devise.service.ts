import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatDevise } from 'app/shared/model/achat-devise.model';

type EntityResponseType = HttpResponse<IAchatDevise>;
type EntityArrayResponseType = HttpResponse<IAchatDevise[]>;

@Injectable({ providedIn: 'root' })
export class AchatDeviseService {
    public resourceUrl = SERVER_API_URL + 'api/achat-devises';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-devises';

    constructor(private http: HttpClient) {}

    create(achatDevise: IAchatDevise): Observable<EntityResponseType> {
        return this.http.post<IAchatDevise>(this.resourceUrl, achatDevise, { observe: 'response' });
    }

    update(achatDevise: IAchatDevise): Observable<EntityResponseType> {
        return this.http.put<IAchatDevise>(this.resourceUrl, achatDevise, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatDevise>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatDevise[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatDevise[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
