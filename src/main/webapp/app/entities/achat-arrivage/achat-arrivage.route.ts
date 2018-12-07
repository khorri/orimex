import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatArrivage } from 'app/shared/model/achat-arrivage.model';
import { AchatArrivageService } from './achat-arrivage.service';
import { AchatArrivageComponent } from './achat-arrivage.component';
import { AchatArrivageDetailComponent } from './achat-arrivage-detail.component';
import { AchatArrivageUpdateComponent } from './achat-arrivage-update.component';
import { AchatArrivageDeletePopupComponent } from './achat-arrivage-delete-dialog.component';
import { IAchatArrivage } from 'app/shared/model/achat-arrivage.model';

@Injectable({ providedIn: 'root' })
export class AchatArrivageResolve implements Resolve<IAchatArrivage> {
    constructor(private service: AchatArrivageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatArrivage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatArrivage>) => response.ok),
                map((achatArrivage: HttpResponse<AchatArrivage>) => achatArrivage.body)
            );
        }
        return of(new AchatArrivage());
    }
}

export const achatArrivageRoute: Routes = [
    {
        path: 'achat-arrivage',
        component: AchatArrivageComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-arrivage/:id/view',
        component: AchatArrivageDetailComponent,
        resolve: {
            achatArrivage: AchatArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-arrivage/new',
        component: AchatArrivageUpdateComponent,
        resolve: {
            achatArrivage: AchatArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-arrivage/:id/edit',
        component: AchatArrivageUpdateComponent,
        resolve: {
            achatArrivage: AchatArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatArrivagePopupRoute: Routes = [
    {
        path: 'achat-arrivage/:id/delete',
        component: AchatArrivageDeletePopupComponent,
        resolve: {
            achatArrivage: AchatArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArrivage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
