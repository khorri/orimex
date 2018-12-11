import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Camion } from 'app/shared/model/camion.model';
import { CamionService } from './camion.service';
import { CamionComponent } from './camion.component';
import { CamionDetailComponent } from './camion-detail.component';
import { CamionUpdateComponent } from './camion-update.component';
import { CamionDeletePopupComponent } from './camion-delete-dialog.component';
import { ICamion } from 'app/shared/model/camion.model';

@Injectable({ providedIn: 'root' })
export class CamionResolve implements Resolve<ICamion> {
    constructor(private service: CamionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Camion> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Camion>) => response.ok),
                map((camion: HttpResponse<Camion>) => camion.body)
            );
        }
        return of(new Camion());
    }
}

export const camionRoute: Routes = [
    {
        path: 'camion',
        component: CamionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.camion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'camion/:id/view',
        component: CamionDetailComponent,
        resolve: {
            camion: CamionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.camion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'camion/new',
        component: CamionUpdateComponent,
        resolve: {
            camion: CamionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.camion.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'camion/:id/edit',
        component: CamionUpdateComponent,
        resolve: {
            camion: CamionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.camion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const camionPopupRoute: Routes = [
    {
        path: 'camion/:id/delete',
        component: CamionDeletePopupComponent,
        resolve: {
            camion: CamionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.camion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
