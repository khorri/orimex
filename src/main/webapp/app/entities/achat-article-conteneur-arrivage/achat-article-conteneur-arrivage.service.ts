import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';

type EntityResponseType = HttpResponse<IAchatArticleConteneurArrivage>;
type EntityArrayResponseType = HttpResponse<IAchatArticleConteneurArrivage[]>;

@Injectable({ providedIn: 'root' })
export class AchatArticleConteneurArrivageService {
    public resourceUrl = SERVER_API_URL + 'api/achat-article-conteneur-arrivages';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-article-conteneur-arrivages';

    constructor(private http: HttpClient) {}

    create(achatArticleConteneurArrivage: IAchatArticleConteneurArrivage): Observable<EntityResponseType> {
        return this.http.post<IAchatArticleConteneurArrivage>(this.resourceUrl, achatArticleConteneurArrivage, { observe: 'response' });
    }

    update(achatArticleConteneurArrivage: IAchatArticleConteneurArrivage): Observable<EntityResponseType> {
        return this.http.put<IAchatArticleConteneurArrivage>(this.resourceUrl, achatArticleConteneurArrivage, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatArticleConteneurArrivage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArticleConteneurArrivage[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArticleConteneurArrivage[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
