import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProfilAction } from 'app/shared/model/profil-action.model';
import { ProfilActionService } from './profil-action.service';
import { ProfilActionComponent } from './profil-action.component';
import { ProfilActionDetailComponent } from './profil-action-detail.component';
import { ProfilActionUpdateComponent } from './profil-action-update.component';
import { ProfilActionDeletePopupComponent } from './profil-action-delete-dialog.component';
import { IProfilAction } from 'app/shared/model/profil-action.model';

@Injectable({ providedIn: 'root' })
export class ProfilActionResolve implements Resolve<IProfilAction> {
    constructor(private service: ProfilActionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProfilAction> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProfilAction>) => response.ok),
                map((profilAction: HttpResponse<ProfilAction>) => profilAction.body)
            );
        }
        return of(new ProfilAction());
    }
}

export const profilActionRoute: Routes = [
    {
        path: 'profil-action',
        component: ProfilActionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.profilAction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-action/:id/view',
        component: ProfilActionDetailComponent,
        resolve: {
            profilAction: ProfilActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilAction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-action/new',
        component: ProfilActionUpdateComponent,
        resolve: {
            profilAction: ProfilActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilAction.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-action/:id/edit',
        component: ProfilActionUpdateComponent,
        resolve: {
            profilAction: ProfilActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilAction.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilActionPopupRoute: Routes = [
    {
        path: 'profil-action/:id/delete',
        component: ProfilActionDeletePopupComponent,
        resolve: {
            profilAction: ProfilActionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilAction.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
