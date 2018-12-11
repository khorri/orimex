import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatDevise } from 'app/shared/model/achat-devise.model';
import { AchatDeviseService } from './achat-devise.service';
import { AchatDeviseComponent } from './achat-devise.component';
import { AchatDeviseDetailComponent } from './achat-devise-detail.component';
import { AchatDeviseUpdateComponent } from './achat-devise-update.component';
import { AchatDeviseDeletePopupComponent } from './achat-devise-delete-dialog.component';
import { IAchatDevise } from 'app/shared/model/achat-devise.model';

@Injectable({ providedIn: 'root' })
export class AchatDeviseResolve implements Resolve<IAchatDevise> {
    constructor(private service: AchatDeviseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatDevise> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatDevise>) => response.ok),
                map((achatDevise: HttpResponse<AchatDevise>) => achatDevise.body)
            );
        }
        return of(new AchatDevise());
    }
}

export const achatDeviseRoute: Routes = [
    {
        path: 'achat-devise',
        component: AchatDeviseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatDevise.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-devise/:id/view',
        component: AchatDeviseDetailComponent,
        resolve: {
            achatDevise: AchatDeviseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDevise.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-devise/new',
        component: AchatDeviseUpdateComponent,
        resolve: {
            achatDevise: AchatDeviseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDevise.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-devise/:id/edit',
        component: AchatDeviseUpdateComponent,
        resolve: {
            achatDevise: AchatDeviseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDevise.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatDevisePopupRoute: Routes = [
    {
        path: 'achat-devise/:id/delete',
        component: AchatDeviseDeletePopupComponent,
        resolve: {
            achatDevise: AchatDeviseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDevise.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
