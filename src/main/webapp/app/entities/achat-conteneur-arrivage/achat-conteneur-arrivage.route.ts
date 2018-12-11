import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';
import { AchatConteneurArrivageService } from './achat-conteneur-arrivage.service';
import { AchatConteneurArrivageComponent } from './achat-conteneur-arrivage.component';
import { AchatConteneurArrivageDetailComponent } from './achat-conteneur-arrivage-detail.component';
import { AchatConteneurArrivageUpdateComponent } from './achat-conteneur-arrivage-update.component';
import { AchatConteneurArrivageDeletePopupComponent } from './achat-conteneur-arrivage-delete-dialog.component';
import { IAchatConteneurArrivage } from 'app/shared/model/achat-conteneur-arrivage.model';

@Injectable({ providedIn: 'root' })
export class AchatConteneurArrivageResolve implements Resolve<IAchatConteneurArrivage> {
    constructor(private service: AchatConteneurArrivageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatConteneurArrivage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatConteneurArrivage>) => response.ok),
                map((achatConteneurArrivage: HttpResponse<AchatConteneurArrivage>) => achatConteneurArrivage.body)
            );
        }
        return of(new AchatConteneurArrivage());
    }
}

export const achatConteneurArrivageRoute: Routes = [
    {
        path: 'achat-conteneur-arrivage',
        component: AchatConteneurArrivageComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-conteneur-arrivage/:id/view',
        component: AchatConteneurArrivageDetailComponent,
        resolve: {
            achatConteneurArrivage: AchatConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-conteneur-arrivage/new',
        component: AchatConteneurArrivageUpdateComponent,
        resolve: {
            achatConteneurArrivage: AchatConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-conteneur-arrivage/:id/edit',
        component: AchatConteneurArrivageUpdateComponent,
        resolve: {
            achatConteneurArrivage: AchatConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatConteneurArrivagePopupRoute: Routes = [
    {
        path: 'achat-conteneur-arrivage/:id/delete',
        component: AchatConteneurArrivageDeletePopupComponent,
        resolve: {
            achatConteneurArrivage: AchatConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
