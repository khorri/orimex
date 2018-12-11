import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IConteneur } from 'app/shared/model/conteneur.model';

type EntityResponseType = HttpResponse<IConteneur>;
type EntityArrayResponseType = HttpResponse<IConteneur[]>;

@Injectable({ providedIn: 'root' })
export class ConteneurService {
    public resourceUrl = SERVER_API_URL + 'api/conteneurs';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/conteneurs';

    constructor(private http: HttpClient) {}

    create(conteneur: IConteneur): Observable<EntityResponseType> {
        return this.http.post<IConteneur>(this.resourceUrl, conteneur, { observe: 'response' });
    }

    update(conteneur: IConteneur): Observable<EntityResponseType> {
        return this.http.put<IConteneur>(this.resourceUrl, conteneur, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IConteneur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConteneur[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IConteneur[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
