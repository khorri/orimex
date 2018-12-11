import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';
import { AchatPrevisionArrivageService } from './achat-prevision-arrivage.service';
import { AchatPrevisionArrivageComponent } from './achat-prevision-arrivage.component';
import { AchatPrevisionArrivageDetailComponent } from './achat-prevision-arrivage-detail.component';
import { AchatPrevisionArrivageUpdateComponent } from './achat-prevision-arrivage-update.component';
import { AchatPrevisionArrivageDeletePopupComponent } from './achat-prevision-arrivage-delete-dialog.component';
import { IAchatPrevisionArrivage } from 'app/shared/model/achat-prevision-arrivage.model';

@Injectable({ providedIn: 'root' })
export class AchatPrevisionArrivageResolve implements Resolve<IAchatPrevisionArrivage> {
    constructor(private service: AchatPrevisionArrivageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatPrevisionArrivage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatPrevisionArrivage>) => response.ok),
                map((achatPrevisionArrivage: HttpResponse<AchatPrevisionArrivage>) => achatPrevisionArrivage.body)
            );
        }
        return of(new AchatPrevisionArrivage());
    }
}

export const achatPrevisionArrivageRoute: Routes = [
    {
        path: 'achat-prevision-arrivage',
        component: AchatPrevisionArrivageComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatPrevisionArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-prevision-arrivage/:id/view',
        component: AchatPrevisionArrivageDetailComponent,
        resolve: {
            achatPrevisionArrivage: AchatPrevisionArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatPrevisionArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-prevision-arrivage/new',
        component: AchatPrevisionArrivageUpdateComponent,
        resolve: {
            achatPrevisionArrivage: AchatPrevisionArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatPrevisionArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-prevision-arrivage/:id/edit',
        component: AchatPrevisionArrivageUpdateComponent,
        resolve: {
            achatPrevisionArrivage: AchatPrevisionArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatPrevisionArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatPrevisionArrivagePopupRoute: Routes = [
    {
        path: 'achat-prevision-arrivage/:id/delete',
        component: AchatPrevisionArrivageDeletePopupComponent,
        resolve: {
            achatPrevisionArrivage: AchatPrevisionArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatPrevisionArrivage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
