import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatFournisseur } from 'app/shared/model/achat-fournisseur.model';
import { AchatFournisseurService } from './achat-fournisseur.service';
import { AchatFournisseurComponent } from './achat-fournisseur.component';
import { AchatFournisseurDetailComponent } from './achat-fournisseur-detail.component';
import { AchatFournisseurUpdateComponent } from './achat-fournisseur-update.component';
import { AchatFournisseurDeletePopupComponent } from './achat-fournisseur-delete-dialog.component';
import { IAchatFournisseur } from 'app/shared/model/achat-fournisseur.model';

@Injectable({ providedIn: 'root' })
export class AchatFournisseurResolve implements Resolve<IAchatFournisseur> {
    constructor(private service: AchatFournisseurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatFournisseur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatFournisseur>) => response.ok),
                map((achatFournisseur: HttpResponse<AchatFournisseur>) => achatFournisseur.body)
            );
        }
        return of(new AchatFournisseur());
    }
}

export const achatFournisseurRoute: Routes = [
    {
        path: 'achat-fournisseur',
        component: AchatFournisseurComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatFournisseur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-fournisseur/:id/view',
        component: AchatFournisseurDetailComponent,
        resolve: {
            achatFournisseur: AchatFournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFournisseur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-fournisseur/new',
        component: AchatFournisseurUpdateComponent,
        resolve: {
            achatFournisseur: AchatFournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFournisseur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-fournisseur/:id/edit',
        component: AchatFournisseurUpdateComponent,
        resolve: {
            achatFournisseur: AchatFournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFournisseur.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatFournisseurPopupRoute: Routes = [
    {
        path: 'achat-fournisseur/:id/delete',
        component: AchatFournisseurDeletePopupComponent,
        resolve: {
            achatFournisseur: AchatFournisseurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatFournisseur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
