import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IFamilleProduit } from 'app/shared/model/famille-produit.model';

type EntityResponseType = HttpResponse<IFamilleProduit>;
type EntityArrayResponseType = HttpResponse<IFamilleProduit[]>;

@Injectable({ providedIn: 'root' })
export class FamilleProduitService {
    public resourceUrl = SERVER_API_URL + 'api/famille-produits';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/famille-produits';

    constructor(private http: HttpClient) {}

    create(familleProduit: IFamilleProduit): Observable<EntityResponseType> {
        return this.http.post<IFamilleProduit>(this.resourceUrl, familleProduit, { observe: 'response' });
    }

    update(familleProduit: IFamilleProduit): Observable<EntityResponseType> {
        return this.http.put<IFamilleProduit>(this.resourceUrl, familleProduit, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IFamilleProduit>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFamilleProduit[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IFamilleProduit[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
