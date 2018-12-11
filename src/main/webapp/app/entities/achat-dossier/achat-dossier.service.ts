import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatDossier } from 'app/shared/model/achat-dossier.model';

type EntityResponseType = HttpResponse<IAchatDossier>;
type EntityArrayResponseType = HttpResponse<IAchatDossier[]>;

@Injectable({ providedIn: 'root' })
export class AchatDossierService {
    public resourceUrl = SERVER_API_URL + 'api/achat-dossiers';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-dossiers';

    constructor(private http: HttpClient) {}

    create(achatDossier: IAchatDossier): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatDossier);
        return this.http
            .post<IAchatDossier>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(achatDossier: IAchatDossier): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatDossier);
        return this.http
            .put<IAchatDossier>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAchatDossier>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatDossier[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatDossier[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(achatDossier: IAchatDossier): IAchatDossier {
        const copy: IAchatDossier = Object.assign({}, achatDossier, {
            dateCreation:
                achatDossier.dateCreation != null && achatDossier.dateCreation.isValid()
                    ? achatDossier.dateCreation.format(DATE_FORMAT)
                    : null,
            dateExpedition:
                achatDossier.dateExpedition != null && achatDossier.dateExpedition.isValid()
                    ? achatDossier.dateExpedition.format(DATE_FORMAT)
                    : null,
            dateOuverture:
                achatDossier.dateOuverture != null && achatDossier.dateOuverture.isValid()
                    ? achatDossier.dateOuverture.format(DATE_FORMAT)
                    : null,
            dateValiditeEi:
                achatDossier.dateValiditeEi != null && achatDossier.dateValiditeEi.isValid()
                    ? achatDossier.dateValiditeEi.format(DATE_FORMAT)
                    : null,
            dateLimiteExp:
                achatDossier.dateLimiteExp != null && achatDossier.dateLimiteExp.isValid()
                    ? achatDossier.dateLimiteExp.format(DATE_FORMAT)
                    : null,
            dateValiditeLc:
                achatDossier.dateValiditeLc != null && achatDossier.dateValiditeLc.isValid()
                    ? achatDossier.dateValiditeLc.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateCreation = res.body.dateCreation != null ? moment(res.body.dateCreation) : null;
            res.body.dateExpedition = res.body.dateExpedition != null ? moment(res.body.dateExpedition) : null;
            res.body.dateOuverture = res.body.dateOuverture != null ? moment(res.body.dateOuverture) : null;
            res.body.dateValiditeEi = res.body.dateValiditeEi != null ? moment(res.body.dateValiditeEi) : null;
            res.body.dateLimiteExp = res.body.dateLimiteExp != null ? moment(res.body.dateLimiteExp) : null;
            res.body.dateValiditeLc = res.body.dateValiditeLc != null ? moment(res.body.dateValiditeLc) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((achatDossier: IAchatDossier) => {
                achatDossier.dateCreation = achatDossier.dateCreation != null ? moment(achatDossier.dateCreation) : null;
                achatDossier.dateExpedition = achatDossier.dateExpedition != null ? moment(achatDossier.dateExpedition) : null;
                achatDossier.dateOuverture = achatDossier.dateOuverture != null ? moment(achatDossier.dateOuverture) : null;
                achatDossier.dateValiditeEi = achatDossier.dateValiditeEi != null ? moment(achatDossier.dateValiditeEi) : null;
                achatDossier.dateLimiteExp = achatDossier.dateLimiteExp != null ? moment(achatDossier.dateLimiteExp) : null;
                achatDossier.dateValiditeLc = achatDossier.dateValiditeLc != null ? moment(achatDossier.dateValiditeLc) : null;
            });
        }
        return res;
    }
}
