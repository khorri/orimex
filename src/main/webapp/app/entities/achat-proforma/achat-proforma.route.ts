import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatProforma } from 'app/shared/model/achat-proforma.model';
import { AchatProformaService } from './achat-proforma.service';
import { AchatProformaComponent } from './achat-proforma.component';
import { AchatProformaDetailComponent } from './achat-proforma-detail.component';
import { AchatProformaUpdateComponent } from './achat-proforma-update.component';
import { AchatProformaDeletePopupComponent } from './achat-proforma-delete-dialog.component';
import { IAchatProforma } from 'app/shared/model/achat-proforma.model';

@Injectable({ providedIn: 'root' })
export class AchatProformaResolve implements Resolve<IAchatProforma> {
    constructor(private service: AchatProformaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatProforma> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatProforma>) => response.ok),
                map((achatProforma: HttpResponse<AchatProforma>) => achatProforma.body)
            );
        }
        return of(new AchatProforma());
    }
}

export const achatProformaRoute: Routes = [
    {
        path: 'achat-proforma',
        component: AchatProformaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-proforma/:id/view',
        component: AchatProformaDetailComponent,
        resolve: {
            achatProforma: AchatProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-proforma/new',
        component: AchatProformaUpdateComponent,
        resolve: {
            achatProforma: AchatProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-proforma/:id/edit',
        component: AchatProformaUpdateComponent,
        resolve: {
            achatProforma: AchatProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatProformaPopupRoute: Routes = [
    {
        path: 'achat-proforma/:id/delete',
        component: AchatProformaDeletePopupComponent,
        resolve: {
            achatProforma: AchatProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatProforma.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
