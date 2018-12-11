import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Recuperation } from 'app/shared/model/recuperation.model';
import { RecuperationService } from './recuperation.service';
import { RecuperationComponent } from './recuperation.component';
import { RecuperationDetailComponent } from './recuperation-detail.component';
import { RecuperationUpdateComponent } from './recuperation-update.component';
import { RecuperationDeletePopupComponent } from './recuperation-delete-dialog.component';
import { IRecuperation } from 'app/shared/model/recuperation.model';

@Injectable({ providedIn: 'root' })
export class RecuperationResolve implements Resolve<IRecuperation> {
    constructor(private service: RecuperationService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Recuperation> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Recuperation>) => response.ok),
                map((recuperation: HttpResponse<Recuperation>) => recuperation.body)
            );
        }
        return of(new Recuperation());
    }
}

export const recuperationRoute: Routes = [
    {
        path: 'recuperation',
        component: RecuperationComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.recuperation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recuperation/:id/view',
        component: RecuperationDetailComponent,
        resolve: {
            recuperation: RecuperationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.recuperation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recuperation/new',
        component: RecuperationUpdateComponent,
        resolve: {
            recuperation: RecuperationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.recuperation.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'recuperation/:id/edit',
        component: RecuperationUpdateComponent,
        resolve: {
            recuperation: RecuperationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.recuperation.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const recuperationPopupRoute: Routes = [
    {
        path: 'recuperation/:id/delete',
        component: RecuperationDeletePopupComponent,
        resolve: {
            recuperation: RecuperationResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.recuperation.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
