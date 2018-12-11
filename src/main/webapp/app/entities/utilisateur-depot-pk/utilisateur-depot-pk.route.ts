import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';
import { UtilisateurDepotPKService } from './utilisateur-depot-pk.service';
import { UtilisateurDepotPKComponent } from './utilisateur-depot-pk.component';
import { UtilisateurDepotPKDetailComponent } from './utilisateur-depot-pk-detail.component';
import { UtilisateurDepotPKUpdateComponent } from './utilisateur-depot-pk-update.component';
import { UtilisateurDepotPKDeletePopupComponent } from './utilisateur-depot-pk-delete-dialog.component';
import { IUtilisateurDepotPK } from 'app/shared/model/utilisateur-depot-pk.model';

@Injectable({ providedIn: 'root' })
export class UtilisateurDepotPKResolve implements Resolve<IUtilisateurDepotPK> {
    constructor(private service: UtilisateurDepotPKService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UtilisateurDepotPK> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UtilisateurDepotPK>) => response.ok),
                map((utilisateurDepotPK: HttpResponse<UtilisateurDepotPK>) => utilisateurDepotPK.body)
            );
        }
        return of(new UtilisateurDepotPK());
    }
}

export const utilisateurDepotPKRoute: Routes = [
    {
        path: 'utilisateur-depot-pk',
        component: UtilisateurDepotPKComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.utilisateurDepotPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-depot-pk/:id/view',
        component: UtilisateurDepotPKDetailComponent,
        resolve: {
            utilisateurDepotPK: UtilisateurDepotPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepotPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-depot-pk/new',
        component: UtilisateurDepotPKUpdateComponent,
        resolve: {
            utilisateurDepotPK: UtilisateurDepotPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepotPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-depot-pk/:id/edit',
        component: UtilisateurDepotPKUpdateComponent,
        resolve: {
            utilisateurDepotPK: UtilisateurDepotPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepotPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utilisateurDepotPKPopupRoute: Routes = [
    {
        path: 'utilisateur-depot-pk/:id/delete',
        component: UtilisateurDepotPKDeletePopupComponent,
        resolve: {
            utilisateurDepotPK: UtilisateurDepotPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepotPK.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
