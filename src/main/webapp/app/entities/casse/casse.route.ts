import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Casse } from 'app/shared/model/casse.model';
import { CasseService } from './casse.service';
import { CasseComponent } from './casse.component';
import { CasseDetailComponent } from './casse-detail.component';
import { CasseUpdateComponent } from './casse-update.component';
import { CasseDeletePopupComponent } from './casse-delete-dialog.component';
import { ICasse } from 'app/shared/model/casse.model';

@Injectable({ providedIn: 'root' })
export class CasseResolve implements Resolve<ICasse> {
    constructor(private service: CasseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Casse> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Casse>) => response.ok),
                map((casse: HttpResponse<Casse>) => casse.body)
            );
        }
        return of(new Casse());
    }
}

export const casseRoute: Routes = [
    {
        path: 'casse',
        component: CasseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.casse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'casse/:id/view',
        component: CasseDetailComponent,
        resolve: {
            casse: CasseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.casse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'casse/new',
        component: CasseUpdateComponent,
        resolve: {
            casse: CasseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.casse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'casse/:id/edit',
        component: CasseUpdateComponent,
        resolve: {
            casse: CasseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.casse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cassePopupRoute: Routes = [
    {
        path: 'casse/:id/delete',
        component: CasseDeletePopupComponent,
        resolve: {
            casse: CasseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.casse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
