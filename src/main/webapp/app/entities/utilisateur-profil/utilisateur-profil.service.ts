import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';

type EntityResponseType = HttpResponse<IUtilisateurProfil>;
type EntityArrayResponseType = HttpResponse<IUtilisateurProfil[]>;

@Injectable({ providedIn: 'root' })
export class UtilisateurProfilService {
    public resourceUrl = SERVER_API_URL + 'api/utilisateur-profils';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/utilisateur-profils';

    constructor(private http: HttpClient) {}

    create(utilisateurProfil: IUtilisateurProfil): Observable<EntityResponseType> {
        return this.http.post<IUtilisateurProfil>(this.resourceUrl, utilisateurProfil, { observe: 'response' });
    }

    update(utilisateurProfil: IUtilisateurProfil): Observable<EntityResponseType> {
        return this.http.put<IUtilisateurProfil>(this.resourceUrl, utilisateurProfil, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IUtilisateurProfil>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUtilisateurProfil[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUtilisateurProfil[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
