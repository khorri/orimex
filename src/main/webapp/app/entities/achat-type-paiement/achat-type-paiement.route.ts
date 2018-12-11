import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';
import { AchatTypePaiementService } from './achat-type-paiement.service';
import { AchatTypePaiementComponent } from './achat-type-paiement.component';
import { AchatTypePaiementDetailComponent } from './achat-type-paiement-detail.component';
import { AchatTypePaiementUpdateComponent } from './achat-type-paiement-update.component';
import { AchatTypePaiementDeletePopupComponent } from './achat-type-paiement-delete-dialog.component';
import { IAchatTypePaiement } from 'app/shared/model/achat-type-paiement.model';

@Injectable({ providedIn: 'root' })
export class AchatTypePaiementResolve implements Resolve<IAchatTypePaiement> {
    constructor(private service: AchatTypePaiementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatTypePaiement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatTypePaiement>) => response.ok),
                map((achatTypePaiement: HttpResponse<AchatTypePaiement>) => achatTypePaiement.body)
            );
        }
        return of(new AchatTypePaiement());
    }
}

export const achatTypePaiementRoute: Routes = [
    {
        path: 'achat-type-paiement',
        component: AchatTypePaiementComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatTypePaiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-type-paiement/:id/view',
        component: AchatTypePaiementDetailComponent,
        resolve: {
            achatTypePaiement: AchatTypePaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatTypePaiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-type-paiement/new',
        component: AchatTypePaiementUpdateComponent,
        resolve: {
            achatTypePaiement: AchatTypePaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatTypePaiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-type-paiement/:id/edit',
        component: AchatTypePaiementUpdateComponent,
        resolve: {
            achatTypePaiement: AchatTypePaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatTypePaiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatTypePaiementPopupRoute: Routes = [
    {
        path: 'achat-type-paiement/:id/delete',
        component: AchatTypePaiementDeletePopupComponent,
        resolve: {
            achatTypePaiement: AchatTypePaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatTypePaiement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
