import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ProfilActionPK } from 'app/shared/model/profil-action-pk.model';
import { ProfilActionPKService } from './profil-action-pk.service';
import { ProfilActionPKComponent } from './profil-action-pk.component';
import { ProfilActionPKDetailComponent } from './profil-action-pk-detail.component';
import { ProfilActionPKUpdateComponent } from './profil-action-pk-update.component';
import { ProfilActionPKDeletePopupComponent } from './profil-action-pk-delete-dialog.component';
import { IProfilActionPK } from 'app/shared/model/profil-action-pk.model';

@Injectable({ providedIn: 'root' })
export class ProfilActionPKResolve implements Resolve<IProfilActionPK> {
    constructor(private service: ProfilActionPKService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ProfilActionPK> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ProfilActionPK>) => response.ok),
                map((profilActionPK: HttpResponse<ProfilActionPK>) => profilActionPK.body)
            );
        }
        return of(new ProfilActionPK());
    }
}

export const profilActionPKRoute: Routes = [
    {
        path: 'profil-action-pk',
        component: ProfilActionPKComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.profilActionPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-action-pk/:id/view',
        component: ProfilActionPKDetailComponent,
        resolve: {
            profilActionPK: ProfilActionPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilActionPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-action-pk/new',
        component: ProfilActionPKUpdateComponent,
        resolve: {
            profilActionPK: ProfilActionPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilActionPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'profil-action-pk/:id/edit',
        component: ProfilActionPKUpdateComponent,
        resolve: {
            profilActionPK: ProfilActionPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilActionPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const profilActionPKPopupRoute: Routes = [
    {
        path: 'profil-action-pk/:id/delete',
        component: ProfilActionPKDeletePopupComponent,
        resolve: {
            profilActionPK: ProfilActionPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.profilActionPK.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
