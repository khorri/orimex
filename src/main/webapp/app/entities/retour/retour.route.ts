import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Retour } from 'app/shared/model/retour.model';
import { RetourService } from './retour.service';
import { RetourComponent } from './retour.component';
import { RetourDetailComponent } from './retour-detail.component';
import { RetourUpdateComponent } from './retour-update.component';
import { RetourDeletePopupComponent } from './retour-delete-dialog.component';
import { IRetour } from 'app/shared/model/retour.model';

@Injectable({ providedIn: 'root' })
export class RetourResolve implements Resolve<IRetour> {
    constructor(private service: RetourService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Retour> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Retour>) => response.ok),
                map((retour: HttpResponse<Retour>) => retour.body)
            );
        }
        return of(new Retour());
    }
}

export const retourRoute: Routes = [
    {
        path: 'retour',
        component: RetourComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.retour.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'retour/:id/view',
        component: RetourDetailComponent,
        resolve: {
            retour: RetourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.retour.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'retour/new',
        component: RetourUpdateComponent,
        resolve: {
            retour: RetourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.retour.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'retour/:id/edit',
        component: RetourUpdateComponent,
        resolve: {
            retour: RetourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.retour.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const retourPopupRoute: Routes = [
    {
        path: 'retour/:id/delete',
        component: RetourDeletePopupComponent,
        resolve: {
            retour: RetourResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.retour.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
