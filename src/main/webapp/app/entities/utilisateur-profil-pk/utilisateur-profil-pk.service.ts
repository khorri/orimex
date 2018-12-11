import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';

type EntityResponseType = HttpResponse<IUtilisateurProfilPK>;
type EntityArrayResponseType = HttpResponse<IUtilisateurProfilPK[]>;

@Injectable({ providedIn: 'root' })
export class UtilisateurProfilPKService {
    public resourceUrl = SERVER_API_URL + 'api/utilisateur-profil-pks';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/utilisateur-profil-pks';

    constructor(private http: HttpClient) {}

    create(utilisateurProfilPK: IUtilisateurProfilPK): Observable<EntityResponseType> {
        return this.http.post<IUtilisateurProfilPK>(this.resourceUrl, utilisateurProfilPK, { observe: 'response' });
    }

    update(utilisateurProfilPK: IUtilisateurProfilPK): Observable<EntityResponseType> {
        return this.http.put<IUtilisateurProfilPK>(this.resourceUrl, utilisateurProfilPK, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IUtilisateurProfilPK>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUtilisateurProfilPK[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUtilisateurProfilPK[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
