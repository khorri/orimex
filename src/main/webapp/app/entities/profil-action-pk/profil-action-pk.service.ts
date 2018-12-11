import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfilActionPK } from 'app/shared/model/profil-action-pk.model';

type EntityResponseType = HttpResponse<IProfilActionPK>;
type EntityArrayResponseType = HttpResponse<IProfilActionPK[]>;

@Injectable({ providedIn: 'root' })
export class ProfilActionPKService {
    public resourceUrl = SERVER_API_URL + 'api/profil-action-pks';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/profil-action-pks';

    constructor(private http: HttpClient) {}

    create(profilActionPK: IProfilActionPK): Observable<EntityResponseType> {
        return this.http.post<IProfilActionPK>(this.resourceUrl, profilActionPK, { observe: 'response' });
    }

    update(profilActionPK: IProfilActionPK): Observable<EntityResponseType> {
        return this.http.put<IProfilActionPK>(this.resourceUrl, profilActionPK, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProfilActionPK>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilActionPK[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilActionPK[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
