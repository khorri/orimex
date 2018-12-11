import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';

type EntityResponseType = HttpResponse<IAchatConteneurArrivage>;
type EntityArrayResponseType = HttpResponse<IAchatConteneurArrivage[]>;

@Injectable({ providedIn: 'root' })
export class AchatConteneurArrivageService {
    public resourceUrl = SERVER_API_URL + 'api/achat-conteneur-arrivages';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-conteneur-arrivages';

    constructor(private http: HttpClient) {}

    create(achatConteneurArrivage: IAchatConteneurArrivage): Observable<EntityResponseType> {
        return this.http.post<IAchatConteneurArrivage>(this.resourceUrl, achatConteneurArrivage, { observe: 'response' });
    }

    update(achatConteneurArrivage: IAchatConteneurArrivage): Observable<EntityResponseType> {
        return this.http.put<IAchatConteneurArrivage>(this.resourceUrl, achatConteneurArrivage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatConteneurArrivage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatConteneurArrivage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatConteneurArrivage[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
