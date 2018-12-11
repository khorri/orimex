import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IOrigine } from 'app/shared/model/origine.model';

type EntityResponseType = HttpResponse<IOrigine>;
type EntityArrayResponseType = HttpResponse<IOrigine[]>;

@Injectable({ providedIn: 'root' })
export class OrigineService {
    public resourceUrl = SERVER_API_URL + 'api/origines';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/origines';

    constructor(private http: HttpClient) {}

    create(origine: IOrigine): Observable<EntityResponseType> {
        return this.http.post<IOrigine>(this.resourceUrl, origine, { observe: 'response' });
    }

    update(origine: IOrigine): Observable<EntityResponseType> {
        return this.http.put<IOrigine>(this.resourceUrl, origine, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IOrigine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOrigine[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IOrigine[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
    }
}
