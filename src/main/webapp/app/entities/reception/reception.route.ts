import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Reception } from 'app/shared/model/reception.model';
import { ReceptionService } from './reception.service';
import { ReceptionComponent } from './reception.component';
import { ReceptionDetailComponent } from './reception-detail.component';
import { ReceptionUpdateComponent } from './reception-update.component';
import { ReceptionDeletePopupComponent } from './reception-delete-dialog.component';
import { IReception } from 'app/shared/model/reception.model';

@Injectable({ providedIn: 'root' })
export class ReceptionResolve implements Resolve<IReception> {
    constructor(private service: ReceptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Reception> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Reception>) => response.ok),
                map((reception: HttpResponse<Reception>) => reception.body)
            );
        }
        return of(new Reception());
    }
}

export const receptionRoute: Routes = [
    {
        path: 'reception',
        component: ReceptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.reception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reception/:id/view',
        component: ReceptionDetailComponent,
        resolve: {
            reception: ReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.reception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reception/new',
        component: ReceptionUpdateComponent,
        resolve: {
            reception: ReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.reception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'reception/:id/edit',
        component: ReceptionUpdateComponent,
        resolve: {
            reception: ReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.reception.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const receptionPopupRoute: Routes = [
    {
        path: 'reception/:id/delete',
        component: ReceptionDeletePopupComponent,
        resolve: {
            reception: ReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.reception.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
