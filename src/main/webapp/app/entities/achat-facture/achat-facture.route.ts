import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatFacture } from 'app/shared/model/achat-facture.model';
import { AchatFactureService } from './achat-facture.service';
import { AchatFactureComponent } from './achat-facture.component';
import { AchatFactureDetailComponent } from './achat-facture-detail.component';
import { AchatFactureUpdateComponent } from './achat-facture-update.component';
import { AchatFactureDeletePopupComponent } from './achat-facture-delete-dialog.component';
import { IAchatFacture } from 'app/shared/model/achat-facture.model';

@Injectable({ providedIn: 'root' })
export class AchatFactureResolve implements Resolve<IAchatFacture> {
    constructor(private service: AchatFactureService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatFacture> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatFacture>) => response.ok),
                map((achatFacture: HttpResponse<AchatFacture>) => achatFacture.body)
            );
        }
        return of(new AchatFacture());
    }
}

export const achatFactureRoute: Routes = [
    {
        path: 'achat-facture',
        component: AchatFactureComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatFacture.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-facture/:id/view',
        component: AchatFactureDetailComponent,
        resolve: {
            achatFacture: AchatFactureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFacture.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-facture/new',
        component: AchatFactureUpdateComponent,
        resolve: {
            achatFacture: AchatFactureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFacture.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-facture/:id/edit',
        component: AchatFactureUpdateComponent,
        resolve: {
            achatFacture: AchatFactureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFacture.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatFacturePopupRoute: Routes = [
    {
        path: 'achat-facture/:id/delete',
        component: AchatFactureDeletePopupComponent,
        resolve: {
            achatFacture: AchatFactureResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFacture.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
