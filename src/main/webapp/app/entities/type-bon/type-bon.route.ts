import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TypeBon } from 'app/shared/model/type-bon.model';
import { TypeBonService } from './type-bon.service';
import { TypeBonComponent } from './type-bon.component';
import { TypeBonDetailComponent } from './type-bon-detail.component';
import { TypeBonUpdateComponent } from './type-bon-update.component';
import { TypeBonDeletePopupComponent } from './type-bon-delete-dialog.component';
import { ITypeBon } from 'app/shared/model/type-bon.model';

@Injectable({ providedIn: 'root' })
export class TypeBonResolve implements Resolve<ITypeBon> {
    constructor(private service: TypeBonService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TypeBon> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<TypeBon>) => response.ok),
                map((typeBon: HttpResponse<TypeBon>) => typeBon.body)
            );
        }
        return of(new TypeBon());
    }
}

export const typeBonRoute: Routes = [
    {
        path: 'type-bon',
        component: TypeBonComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.typeBon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-bon/:id/view',
        component: TypeBonDetailComponent,
        resolve: {
            typeBon: TypeBonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.typeBon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-bon/new',
        component: TypeBonUpdateComponent,
        resolve: {
            typeBon: TypeBonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.typeBon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'type-bon/:id/edit',
        component: TypeBonUpdateComponent,
        resolve: {
            typeBon: TypeBonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.typeBon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const typeBonPopupRoute: Routes = [
    {
        path: 'type-bon/:id/delete',
        component: TypeBonDeletePopupComponent,
        resolve: {
            typeBon: TypeBonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.typeBon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
