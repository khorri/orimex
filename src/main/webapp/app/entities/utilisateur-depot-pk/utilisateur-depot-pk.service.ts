import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';

type EntityResponseType = HttpResponse<IUtilisateurDepotPK>;
type EntityArrayResponseType = HttpResponse<IUtilisateurDepotPK[]>;

@Injectable({ providedIn: 'root' })
export class UtilisateurDepotPKService {
    public resourceUrl = SERVER_API_URL + 'api/utilisateur-depot-pks';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/utilisateur-depot-pks';

    constructor(private http: HttpClient) {}

    create(utilisateurDepotPK: IUtilisateurDepotPK): Observable<EntityResponseType> {
        return this.http.post<IUtilisateurDepotPK>(this.resourceUrl, utilisateurDepotPK, { observe: 'response' });
    }

    update(utilisateurDepotPK: IUtilisateurDepotPK): Observable<EntityResponseType> {
        return this.http.put<IUtilisateurDepotPK>(this.resourceUrl, utilisateurDepotPK, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IUtilisateurDepotPK>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUtilisateurDepotPK[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUtilisateurDepotPK[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
