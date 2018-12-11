import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';

type EntityResponseType = HttpResponse<IAchatArticleLigneProforma>;
type EntityArrayResponseType = HttpResponse<IAchatArticleLigneProforma[]>;

@Injectable({ providedIn: 'root' })
export class AchatArticleLigneProformaService {
    public resourceUrl = SERVER_API_URL + 'api/achat-article-ligne-proformas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-article-ligne-proformas';

    constructor(private http: HttpClient) {}

    create(achatArticleLigneProforma: IAchatArticleLigneProforma): Observable<EntityResponseType> {
        return this.http.post<IAchatArticleLigneProforma>(this.resourceUrl, achatArticleLigneProforma, { observe: 'response' });
    }

    update(achatArticleLigneProforma: IAchatArticleLigneProforma): Observable<EntityResponseType> {
        return this.http.put<IAchatArticleLigneProforma>(this.resourceUrl, achatArticleLigneProforma, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatArticleLigneProforma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArticleLigneProforma[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatArticleLigneProforma[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
