import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';

type EntityResponseType = HttpResponse<IAchatLigneProforma>;
type EntityArrayResponseType = HttpResponse<IAchatLigneProforma[]>;

@Injectable({ providedIn: 'root' })
export class AchatLigneProformaService {
    public resourceUrl = SERVER_API_URL + 'api/achat-ligne-proformas';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-ligne-proformas';

    constructor(private http: HttpClient) {}

    create(achatLigneProforma: IAchatLigneProforma): Observable<EntityResponseType> {
        return this.http.post<IAchatLigneProforma>(this.resourceUrl, achatLigneProforma, { observe: 'response' });
    }

    update(achatLigneProforma: IAchatLigneProforma): Observable<EntityResponseType> {
        return this.http.put<IAchatLigneProforma>(this.resourceUrl, achatLigneProforma, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatLigneProforma>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatLigneProforma[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatLigneProforma[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
