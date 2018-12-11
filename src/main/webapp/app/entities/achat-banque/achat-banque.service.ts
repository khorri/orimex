import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatBanque } from 'app/shared/model/achat-banque.model';

type EntityResponseType = HttpResponse<IAchatBanque>;
type EntityArrayResponseType = HttpResponse<IAchatBanque[]>;

@Injectable({ providedIn: 'root' })
export class AchatBanqueService {
    public resourceUrl = SERVER_API_URL + 'api/achat-banques';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-banques';

    constructor(private http: HttpClient) {}

    create(achatBanque: IAchatBanque): Observable<EntityResponseType> {
        return this.http.post<IAchatBanque>(this.resourceUrl, achatBanque, { observe: 'response' });
    }

    update(achatBanque: IAchatBanque): Observable<EntityResponseType> {
        return this.http.put<IAchatBanque>(this.resourceUrl, achatBanque, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAchatBanque>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatBanque[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAchatBanque[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
