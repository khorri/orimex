import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Origine } from 'app/shared/model/origine.model';
import { OrigineService } from './origine.service';
import { OrigineComponent } from './origine.component';
import { OrigineDetailComponent } from './origine-detail.component';
import { OrigineUpdateComponent } from './origine-update.component';
import { OrigineDeletePopupComponent } from './origine-delete-dialog.component';
import { IOrigine } from 'app/shared/model/origine.model';

@Injectable({ providedIn: 'root' })
export class OrigineResolve implements Resolve<IOrigine> {
    constructor(private service: OrigineService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Origine> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Origine>) => response.ok),
                map((origine: HttpResponse<Origine>) => origine.body)
            );
        }
        return of(new Origine());
    }
}

export const origineRoute: Routes = [
    {
        path: 'origine',
        component: OrigineComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.origine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'origine/:id/view',
        component: OrigineDetailComponent,
        resolve: {
            origine: OrigineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.origine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'origine/new',
        component: OrigineUpdateComponent,
        resolve: {
            origine: OrigineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.origine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'origine/:id/edit',
        component: OrigineUpdateComponent,
        resolve: {
            origine: OrigineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.origine.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const originePopupRoute: Routes = [
    {
        path: 'origine/:id/delete',
        component: OrigineDeletePopupComponent,
        resolve: {
            origine: OrigineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.origine.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
