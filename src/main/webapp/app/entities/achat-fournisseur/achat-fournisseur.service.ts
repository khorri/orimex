import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatFournisseur } from 'app/shared/model/achat-fournisseur.model';

type EntityResponseType = HttpResponse<IAchatFournisseur>;
type EntityArrayResponseType = HttpResponse<IAchatFournisseur[]>;

@Injectable({ providedIn: 'root' })
export class AchatFournisseurService {
    public resourceUrl = SERVER_API_URL + 'api/achat-fournisseurs';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-fournisseurs';

    constructor(private http: HttpClient) {}

    create(achatFournisseur: IAchatFournisseur): Observable<EntityResponseType> {
        return this.http.post<IAchatFournisseur>(this.resourceUrl, achatFournisseur, { observe: 'response' });
    }

    update(achatFournisseur: IAchatFournisseur): Observable<EntityResponseType> {
        return this.http.put<IAchatFournisseur>(this.resourceUrl, achatFournisseur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatFournisseur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatFournisseur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatFournisseur[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
