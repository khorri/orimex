import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';
import { AchatStatutDossierService } from './achat-statut-dossier.service';
import { AchatStatutDossierComponent } from './achat-statut-dossier.component';
import { AchatStatutDossierDetailComponent } from './achat-statut-dossier-detail.component';
import { AchatStatutDossierUpdateComponent } from './achat-statut-dossier-update.component';
import { AchatStatutDossierDeletePopupComponent } from './achat-statut-dossier-delete-dialog.component';
import { IAchatStatutDossier } from 'app/shared/model/achat-statut-dossier.model';

@Injectable({ providedIn: 'root' })
export class AchatStatutDossierResolve implements Resolve<IAchatStatutDossier> {
    constructor(private service: AchatStatutDossierService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatStatutDossier> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatStatutDossier>) => response.ok),
                map((achatStatutDossier: HttpResponse<AchatStatutDossier>) => achatStatutDossier.body)
            );
        }
        return of(new AchatStatutDossier());
    }
}

export const achatStatutDossierRoute: Routes = [
    {
        path: 'achat-statut-dossier',
        component: AchatStatutDossierComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatStatutDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-statut-dossier/:id/view',
        component: AchatStatutDossierDetailComponent,
        resolve: {
            achatStatutDossier: AchatStatutDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatStatutDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-statut-dossier/new',
        component: AchatStatutDossierUpdateComponent,
        resolve: {
            achatStatutDossier: AchatStatutDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatStatutDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-statut-dossier/:id/edit',
        component: AchatStatutDossierUpdateComponent,
        resolve: {
            achatStatutDossier: AchatStatutDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatStatutDossier.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatStatutDossierPopupRoute: Routes = [
    {
        path: 'achat-statut-dossier/:id/delete',
        component: AchatStatutDossierDeletePopupComponent,
        resolve: {
            achatStatutDossier: AchatStatutDossierResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatStatutDossier.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
