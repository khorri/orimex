import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAchatFacture } from 'app/shared/model/achat-facture.model';

type EntityResponseType = HttpResponse<IAchatFacture>;
type EntityArrayResponseType = HttpResponse<IAchatFacture[]>;

@Injectable({ providedIn: 'root' })
export class AchatFactureService {
    public resourceUrl = SERVER_API_URL + 'api/achat-factures';
    public resourceSearchUrl = SERVER_API_URL + 'api/_search/achat-factures';

    constructor(private http: HttpClient) {}

    create(achatFacture: IAchatFacture): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatFacture);
        return this.http
            .post<IAchatFacture>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(achatFacture: IAchatFacture): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(achatFacture);
        return this.http
            .put<IAchatFacture>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAchatFacture>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatFacture[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAchatFacture[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    protected convertDateFromClient(achatFacture: IAchatFacture): IAchatFacture {
        const copy: IAchatFacture = Object.assign({}, achatFacture, {
            dateFacture:
                achatFacture.dateFacture != null && achatFacture.dateFacture.isValid()
                    ? achatFacture.dateFacture.format(DATE_FORMAT)
                    : null,
            dateBl: achatFacture.dateBl != null && achatFacture.dateBl.isValid() ? achatFacture.dateBl.format(DATE_FORMAT) : null,
            dateEcheance:
                achatFacture.dateEcheance != null && achatFacture.dateEcheance.isValid()
                    ? achatFacture.dateEcheance.format(DATE_FORMAT)
                    : null,
            dateValeur:
                achatFacture.dateValeur != null && achatFacture.dateValeur.isValid() ? achatFacture.dateValeur.format(DATE_FORMAT) : null,
            echecanceFinancement:
                achatFacture.echecanceFinancement != null && achatFacture.echecanceFinancement.isValid()
                    ? achatFacture.echecanceFinancement.format(DATE_FORMAT)
                    : null,
            dateReglement:
                achatFacture.dateReglement != null && achatFacture.dateReglement.isValid()
                    ? achatFacture.dateReglement.format(DATE_FORMAT)
                    : null,
            derniereEcheance:
                achatFacture.derniereEcheance != null && achatFacture.derniereEcheance.isValid()
                    ? achatFacture.derniereEcheance.format(DATE_FORMAT)
                    : null,
            echeanceRefinancement:
                achatFacture.echeanceRefinancement != null && achatFacture.echeanceRefinancement.isValid()
                    ? achatFacture.echeanceRefinancement.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateFacture = res.body.dateFacture != null ? moment(res.body.dateFacture) : null;
            res.body.dateBl = res.body.dateBl != null ? moment(res.body.dateBl) : null;
            res.body.dateEcheance = res.body.dateEcheance != null ? moment(res.body.dateEcheance) : null;
            res.body.dateValeur = res.body.dateValeur != null ? moment(res.body.dateValeur) : null;
            res.body.echecanceFinancement = res.body.echecanceFinancement != null ? moment(res.body.echecanceFinancement) : null;
            res.body.dateReglement = res.body.dateReglement != null ? moment(res.body.dateReglement) : null;
            res.body.derniereEcheance = res.body.derniereEcheance != null ? moment(res.body.derniereEcheance) : null;
            res.body.echeanceRefinancement = res.body.echeanceRefinancement != null ? moment(res.body.echeanceRefinancement) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((achatFacture: IAchatFacture) => {
                achatFacture.dateFacture = achatFacture.dateFacture != null ? moment(achatFacture.dateFacture) : null;
                achatFacture.dateBl = achatFacture.dateBl != null ? moment(achatFacture.dateBl) : null;
                achatFacture.dateEcheance = achatFacture.dateEcheance != null ? moment(achatFacture.dateEcheance) : null;
                achatFacture.dateValeur = achatFacture.dateValeur != null ? moment(achatFacture.dateValeur) : null;
                achatFacture.echecanceFinancement =
                    achatFacture.echecanceFinancement != null ? moment(achatFacture.echecanceFinancement) : null;
                achatFacture.dateReglement = achatFacture.dateReglement != null ? moment(achatFacture.dateReglement) : null;
                achatFacture.derniereEcheance = achatFacture.derniereEcheance != null ? moment(achatFacture.derniereEcheance) : null;
                achatFacture.echeanceRefinancement =
                    achatFacture.echeanceRefinancement != null ? moment(achatFacture.echeanceRefinancement) : null;
            });
        }
        return res;
    }
}
