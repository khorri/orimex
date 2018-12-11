import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatBanque } from 'app/shared/model/achat-banque.model';
import { AchatBanqueService } from './achat-banque.service';
import { AchatBanqueComponent } from './achat-banque.component';
import { AchatBanqueDetailComponent } from './achat-banque-detail.component';
import { AchatBanqueUpdateComponent } from './achat-banque-update.component';
import { AchatBanqueDeletePopupComponent } from './achat-banque-delete-dialog.component';
import { IAchatBanque } from 'app/shared/model/achat-banque.model';

@Injectable({ providedIn: 'root' })
export class AchatBanqueResolve implements Resolve<IAchatBanque> {
    constructor(private service: AchatBanqueService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatBanque> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatBanque>) => response.ok),
                map((achatBanque: HttpResponse<AchatBanque>) => achatBanque.body)
            );
        }
        return of(new AchatBanque());
    }
}

export const achatBanqueRoute: Routes = [
    {
        path: 'achat-banque',
        component: AchatBanqueComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatBanque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-banque/:id/view',
        component: AchatBanqueDetailComponent,
        resolve: {
            achatBanque: AchatBanqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatBanque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-banque/new',
        component: AchatBanqueUpdateComponent,
        resolve: {
            achatBanque: AchatBanqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatBanque.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-banque/:id/edit',
        component: AchatBanqueUpdateComponent,
        resolve: {
            achatBanque: AchatBanqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatBanque.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatBanquePopupRoute: Routes = [
    {
        path: 'achat-banque/:id/delete',
        component: AchatBanqueDeletePopupComponent,
        resolve: {
            achatBanque: AchatBanqueResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatBanque.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
