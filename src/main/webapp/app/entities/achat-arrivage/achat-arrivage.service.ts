import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

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
        return this.http.post<IAchatArrivage>(this.resourceUrl, achatArrivage, { observe: 'response' });
    }

    update(achatArrivage: IAchatArrivage): Observable<EntityResponseType> {
        return this.http.put<IAchatArrivage>(this.resourceUrl, achatArrivage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatArrivage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArrivage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArrivage[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
