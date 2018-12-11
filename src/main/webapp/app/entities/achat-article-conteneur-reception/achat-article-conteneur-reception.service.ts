import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';

type EntityResponseType = HttpResponse<IAchatArticleConteneurReception>;
type EntityArrayResponseType = HttpResponse<IAchatArticleConteneurReception[]>;

@Injectable({ providedIn: 'root' })
export class AchatArticleConteneurReceptionService {
    public resourceUrl = SERVER_API_URL + 'api/achat-article-conteneur-receptions';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-article-conteneur-receptions';

    constructor(private http: HttpClient) {}

    create(achatArticleConteneurReception: IAchatArticleConteneurReception): Observable<EntityResponseType> {
        return this.http.post<IAchatArticleConteneurReception>(this.resourceUrl, achatArticleConteneurReception, { observe: 'response' });
    }

    update(achatArticleConteneurReception: IAchatArticleConteneurReception): Observable<EntityResponseType> {
        return this.http.put<IAchatArticleConteneurReception>(this.resourceUrl, achatArticleConteneurReception, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatArticleConteneurReception>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArticleConteneurReception[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArticleConteneurReception[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
