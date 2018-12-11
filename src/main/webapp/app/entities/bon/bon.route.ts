import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Bon } from 'app/shared/model/bon.model';
import { BonService } from './bon.service';
import { BonComponent } from './bon.component';
import { BonDetailComponent } from './bon-detail.component';
import { BonUpdateComponent } from './bon-update.component';
import { BonDeletePopupComponent } from './bon-delete-dialog.component';
import { IBon } from 'app/shared/model/bon.model';

@Injectable({ providedIn: 'root' })
export class BonResolve implements Resolve<IBon> {
    constructor(private service: BonService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Bon> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Bon>) => response.ok),
                map((bon: HttpResponse<Bon>) => bon.body)
            );
        }
        return of(new Bon());
    }
}

export const bonRoute: Routes = [
    {
        path: 'bon',
        component: BonComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.bon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bon/:id/view',
        component: BonDetailComponent,
        resolve: {
            bon: BonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.bon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bon/new',
        component: BonUpdateComponent,
        resolve: {
            bon: BonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.bon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'bon/:id/edit',
        component: BonUpdateComponent,
        resolve: {
            bon: BonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.bon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bonPopupRoute: Routes = [
    {
        path: 'bon/:id/delete',
        component: BonDeletePopupComponent,
        resolve: {
            bon: BonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.bon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
