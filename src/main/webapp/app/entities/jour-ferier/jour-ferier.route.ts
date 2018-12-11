import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JourFerier } from 'app/shared/model/jour-ferier.model';
import { JourFerierService } from './jour-ferier.service';
import { JourFerierComponent } from './jour-ferier.component';
import { JourFerierDetailComponent } from './jour-ferier-detail.component';
import { JourFerierUpdateComponent } from './jour-ferier-update.component';
import { JourFerierDeletePopupComponent } from './jour-ferier-delete-dialog.component';
import { IJourFerier } from 'app/shared/model/jour-ferier.model';

@Injectable({ providedIn: 'root' })
export class JourFerierResolve implements Resolve<IJourFerier> {
    constructor(private service: JourFerierService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<JourFerier> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<JourFerier>) => response.ok),
                map((jourFerier: HttpResponse<JourFerier>) => jourFerier.body)
            );
        }
        return of(new JourFerier());
    }
}

export const jourFerierRoute: Routes = [
    {
        path: 'jour-ferier',
        component: JourFerierComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.jourFerier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jour-ferier/:id/view',
        component: JourFerierDetailComponent,
        resolve: {
            jourFerier: JourFerierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.jourFerier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jour-ferier/new',
        component: JourFerierUpdateComponent,
        resolve: {
            jourFerier: JourFerierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.jourFerier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'jour-ferier/:id/edit',
        component: JourFerierUpdateComponent,
        resolve: {
            jourFerier: JourFerierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.jourFerier.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const jourFerierPopupRoute: Routes = [
    {
        path: 'jour-ferier/:id/delete',
        component: JourFerierDeletePopupComponent,
        resolve: {
            jourFerier: JourFerierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.jourFerier.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
