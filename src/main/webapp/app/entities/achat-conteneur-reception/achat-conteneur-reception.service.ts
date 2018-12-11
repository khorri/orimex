import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';

type EntityResponseType = HttpResponse<IAchatConteneurReception>;
type EntityArrayResponseType = HttpResponse<IAchatConteneurReception[]>;

@Injectable({ providedIn: 'root' })
export class AchatConteneurReceptionService {
    public resourceUrl = SERVER_API_URL + 'api/achat-conteneur-receptions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-conteneur-receptions';

    constructor(private http: HttpClient) {}

    create(achatConteneurReception: IAchatConteneurReception): Observable<EntityResponseType> {
        return this.http.post<IAchatConteneurReception>(this.resourceUrl, achatConteneurReception, { observe: 'response' });
    }

    update(achatConteneurReception: IAchatConteneurReception): Observable<EntityResponseType> {
        return this.http.put<IAchatConteneurReception>(this.resourceUrl, achatConteneurReception, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatConteneurReception>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatConteneurReception[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatConteneurReception[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
