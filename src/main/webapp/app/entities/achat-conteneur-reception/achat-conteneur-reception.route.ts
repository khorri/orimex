import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';
import { AchatConteneurReceptionService } from './achat-conteneur-reception.service';
import { AchatConteneurReceptionComponent } from './achat-conteneur-reception.component';
import { AchatConteneurReceptionDetailComponent } from './achat-conteneur-reception-detail.component';
import { AchatConteneurReceptionUpdateComponent } from './achat-conteneur-reception-update.component';
import { AchatConteneurReceptionDeletePopupComponent } from './achat-conteneur-reception-delete-dialog.component';
import { IAchatConteneurReception } from 'app/shared/model/achat-conteneur-reception.model';

@Injectable({ providedIn: 'root' })
export class AchatConteneurReceptionResolve implements Resolve<IAchatConteneurReception> {
    constructor(private service: AchatConteneurReceptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatConteneurReception> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatConteneurReception>) => response.ok),
                map((achatConteneurReception: HttpResponse<AchatConteneurReception>) => achatConteneurReception.body)
            );
        }
        return of(new AchatConteneurReception());
    }
}

export const achatConteneurReceptionRoute: Routes = [
    {
        path: 'achat-conteneur-reception',
        component: AchatConteneurReceptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-conteneur-reception/:id/view',
        component: AchatConteneurReceptionDetailComponent,
        resolve: {
            achatConteneurReception: AchatConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-conteneur-reception/new',
        component: AchatConteneurReceptionUpdateComponent,
        resolve: {
            achatConteneurReception: AchatConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-conteneur-reception/:id/edit',
        component: AchatConteneurReceptionUpdateComponent,
        resolve: {
            achatConteneurReception: AchatConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatConteneurReceptionPopupRoute: Routes = [
    {
        path: 'achat-conteneur-reception/:id/delete',
        component: AchatConteneurReceptionDeletePopupComponent,
        resolve: {
            achatConteneurReception: AchatConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
