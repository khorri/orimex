import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IProfilAction } from 'app/shared/model/profil-action.model';

type EntityResponseType = HttpResponse<IProfilAction>;
type EntityArrayResponseType = HttpResponse<IProfilAction[]>;

@Injectable({ providedIn: 'root' })
export class ProfilActionService {
    public resourceUrl = SERVER_API_URL + 'api/profil-actions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/profil-actions';

    constructor(private http: HttpClient) {}

    create(profilAction: IProfilAction): Observable<EntityResponseType> {
        return this.http.post<IProfilAction>(this.resourceUrl, profilAction, { observe: 'response' });
    }

    update(profilAction: IProfilAction): Observable<EntityResponseType> {
        return this.http.put<IProfilAction>(this.resourceUrl, profilAction, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IProfilAction>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilAction[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IProfilAction[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
