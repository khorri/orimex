import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Depot } from 'app/shared/model/depot.model';
import { DepotService } from './depot.service';
import { DepotComponent } from './depot.component';
import { DepotDetailComponent } from './depot-detail.component';
import { DepotUpdateComponent } from './depot-update.component';
import { DepotDeletePopupComponent } from './depot-delete-dialog.component';
import { IDepot } from 'app/shared/model/depot.model';

@Injectable({ providedIn: 'root' })
export class DepotResolve implements Resolve<IDepot> {
    constructor(private service: DepotService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Depot> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Depot>) => response.ok),
                map((depot: HttpResponse<Depot>) => depot.body)
            );
        }
        return of(new Depot());
    }
}

export const depotRoute: Routes = [
    {
        path: 'depot',
        component: DepotComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.depot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'depot/:id/view',
        component: DepotDetailComponent,
        resolve: {
            depot: DepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.depot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'depot/new',
        component: DepotUpdateComponent,
        resolve: {
            depot: DepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.depot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'depot/:id/edit',
        component: DepotUpdateComponent,
        resolve: {
            depot: DepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.depot.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const depotPopupRoute: Routes = [
    {
        path: 'depot/:id/delete',
        component: DepotDeletePopupComponent,
        resolve: {
            depot: DepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.depot.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
