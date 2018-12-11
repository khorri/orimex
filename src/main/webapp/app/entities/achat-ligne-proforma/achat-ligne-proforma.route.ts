import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';
import { AchatLigneProformaService } from './achat-ligne-proforma.service';
import { AchatLigneProformaComponent } from './achat-ligne-proforma.component';
import { AchatLigneProformaDetailComponent } from './achat-ligne-proforma-detail.component';
import { AchatLigneProformaUpdateComponent } from './achat-ligne-proforma-update.component';
import { AchatLigneProformaDeletePopupComponent } from './achat-ligne-proforma-delete-dialog.component';
import { IAchatLigneProforma } from 'app/shared/model/achat-ligne-proforma.model';

@Injectable({ providedIn: 'root' })
export class AchatLigneProformaResolve implements Resolve<IAchatLigneProforma> {
    constructor(private service: AchatLigneProformaService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatLigneProforma> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatLigneProforma>) => response.ok),
                map((achatLigneProforma: HttpResponse<AchatLigneProforma>) => achatLigneProforma.body)
            );
        }
        return of(new AchatLigneProforma());
    }
}

export const achatLigneProformaRoute: Routes = [
    {
        path: 'achat-ligne-proforma',
        component: AchatLigneProformaComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-ligne-proforma/:id/view',
        component: AchatLigneProformaDetailComponent,
        resolve: {
            achatLigneProforma: AchatLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-ligne-proforma/new',
        component: AchatLigneProformaUpdateComponent,
        resolve: {
            achatLigneProforma: AchatLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-ligne-proforma/:id/edit',
        component: AchatLigneProformaUpdateComponent,
        resolve: {
            achatLigneProforma: AchatLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatLigneProformaPopupRoute: Routes = [
    {
        path: 'achat-ligne-proforma/:id/delete',
        component: AchatLigneProformaDeletePopupComponent,
        resolve: {
            achatLigneProforma: AchatLigneProformaResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatLigneProforma.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
