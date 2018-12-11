import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Caisse } from 'app/shared/model/caisse.model';
import { CaisseService } from './caisse.service';
import { CaisseComponent } from './caisse.component';
import { CaisseDetailComponent } from './caisse-detail.component';
import { CaisseUpdateComponent } from './caisse-update.component';
import { CaisseDeletePopupComponent } from './caisse-delete-dialog.component';
import { ICaisse } from 'app/shared/model/caisse.model';

@Injectable({ providedIn: 'root' })
export class CaisseResolve implements Resolve<ICaisse> {
    constructor(private service: CaisseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Caisse> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Caisse>) => response.ok),
                map((caisse: HttpResponse<Caisse>) => caisse.body)
            );
        }
        return of(new Caisse());
    }
}

export const caisseRoute: Routes = [
    {
        path: 'caisse',
        component: CaisseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'caisse/:id/view',
        component: CaisseDetailComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'caisse/new',
        component: CaisseUpdateComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'caisse/:id/edit',
        component: CaisseUpdateComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const caissePopupRoute: Routes = [
    {
        path: 'caisse/:id/delete',
        component: CaisseDeletePopupComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
