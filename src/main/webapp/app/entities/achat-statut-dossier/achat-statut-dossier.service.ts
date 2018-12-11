import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';

type EntityResponseType = HttpResponse<IAchatStatutDossier>;
type EntityArrayResponseType = HttpResponse<IAchatStatutDossier[]>;

@Injectable({ providedIn: 'root' })
export class AchatStatutDossierService {
    public resourceUrl = SERVER_API_URL + 'api/achat-statut-dossiers';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-statut-dossiers';

    constructor(private http: HttpClient) {}

    create(achatStatutDossier: IAchatStatutDossier): Observable<EntityResponseType> {
        return this.http.post<IAchatStatutDossier>(this.resourceUrl, achatStatutDossier, { observe: 'response' });
    }

    update(achatStatutDossier: IAchatStatutDossier): Observable<EntityResponseType> {
        return this.http.put<IAchatStatutDossier>(this.resourceUrl, achatStatutDossier, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatStatutDossier>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatStatutDossier[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatStatutDossier[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
