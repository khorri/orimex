import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatDossier } from 'app/shared/model/achat-dossier.model';
import { AchatDossierService } from './achat-dossier.service';
import { AchatDossierComponent } from './achat-dossier.component';
import { AchatDossierDetailComponent } from './achat-dossier-detail.component';
import { AchatDossierUpdateComponent } from './achat-dossier-update.component';
import { AchatDossierDeletePopupComponent } from './achat-dossier-delete-dialog.component';
import { IAchatDossier } from 'app/shared/model/achat-dossier.model';

@Injectable({ providedIn: 'root' })
export class AchatDossierResolve implements Resolve<IAchatDossier> {
    constructor(private service: AchatDossierService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatDossier> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatDossier>) => response.ok),
                map((achatDossier: HttpResponse<AchatDossier>) => achatDossier.body)
            );
        }
        return of(new AchatDossier());
    }
}

export const achatDossierRoute: Routes = [
    {
        path: 'achat-dossier',
        component: AchatDossierComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-dossier/:id/view',
        component: AchatDossierDetailComponent,
        resolve: {
            achatDossier: AchatDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-dossier/new',
        component: AchatDossierUpdateComponent,
        resolve: {
            achatDossier: AchatDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-dossier/:id/edit',
        component: AchatDossierUpdateComponent,
        resolve: {
            achatDossier: AchatDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatDossierPopupRoute: Routes = [
    {
        path: 'achat-dossier/:id/delete',
        component: AchatDossierDeletePopupComponent,
        resolve: {
            achatDossier: AchatDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatDossier.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
