import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';

type EntityResponseType = HttpResponse<IAchatTypePaiement>;
type EntityArrayResponseType = HttpResponse<IAchatTypePaiement[]>;

@Injectable({ providedIn: 'root' })
export class AchatTypePaiementService {
    public resourceUrl = SERVER_API_URL + 'api/achat-type-paiements';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-type-paiements';

    constructor(private http: HttpClient) {}

    create(achatTypePaiement: IAchatTypePaiement): Observable<EntityResponseType> {
        return this.http.post<IAchatTypePaiement>(this.resourceUrl, achatTypePaiement, { observe: 'response' });
    }

    update(achatTypePaiement: IAchatTypePaiement): Observable<EntityResponseType> {
        return this.http.put<IAchatTypePaiement>(this.resourceUrl, achatTypePaiement, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatTypePaiement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatTypePaiement[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatTypePaiement[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
