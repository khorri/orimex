import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';
import { AchatArticleLigneProformaService } from './achat-article-ligne-proforma.service';
import { AchatArticleLigneProformaComponent } from './achat-article-ligne-proforma.component';
import { AchatArticleLigneProformaDetailComponent } from './achat-article-ligne-proforma-detail.component';
import { AchatArticleLigneProformaUpdateComponent } from './achat-article-ligne-proforma-update.component';
import { AchatArticleLigneProformaDeletePopupComponent } from './achat-article-ligne-proforma-delete-dialog.component';
import { IAchatArticleLigneProforma } from 'app/shared/model/achat-article-ligne-proforma.model';

@Injectable({ providedIn: 'root' })
export class AchatArticleLigneProformaResolve implements Resolve<IAchatArticleLigneProforma> {
    constructor(private service: AchatArticleLigneProformaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatArticleLigneProforma> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatArticleLigneProforma>) => response.ok),
                map((achatArticleLigneProforma: HttpResponse<AchatArticleLigneProforma>) => achatArticleLigneProforma.body)
            );
        }
        return of(new AchatArticleLigneProforma());
    }
}

export const achatArticleLigneProformaRoute: Routes = [
    {
        path: 'achat-article-ligne-proforma',
        component: AchatArticleLigneProformaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatArticleLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-ligne-proforma/:id/view',
        component: AchatArticleLigneProformaDetailComponent,
        resolve: {
            achatArticleLigneProforma: AchatArticleLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-ligne-proforma/new',
        component: AchatArticleLigneProformaUpdateComponent,
        resolve: {
            achatArticleLigneProforma: AchatArticleLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-ligne-proforma/:id/edit',
        component: AchatArticleLigneProformaUpdateComponent,
        resolve: {
            achatArticleLigneProforma: AchatArticleLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatArticleLigneProformaPopupRoute: Routes = [
    {
        path: 'achat-article-ligne-proforma/:id/delete',
        component: AchatArticleLigneProformaDeletePopupComponent,
        resolve: {
            achatArticleLigneProforma: AchatArticleLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
