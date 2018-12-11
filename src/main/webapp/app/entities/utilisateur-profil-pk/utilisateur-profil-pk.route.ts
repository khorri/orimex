import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';
import { UtilisateurProfilPKService } from './utilisateur-profil-pk.service';
import { UtilisateurProfilPKComponent } from './utilisateur-profil-pk.component';
import { UtilisateurProfilPKDetailComponent } from './utilisateur-profil-pk-detail.component';
import { UtilisateurProfilPKUpdateComponent } from './utilisateur-profil-pk-update.component';
import { UtilisateurProfilPKDeletePopupComponent } from './utilisateur-profil-pk-delete-dialog.component';
import { IUtilisateurProfilPK } from 'app/shared/model/utilisateur-profil-pk.model';

@Injectable({ providedIn: 'root' })
export class UtilisateurProfilPKResolve implements Resolve<IUtilisateurProfilPK> {
    constructor(private service: UtilisateurProfilPKService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UtilisateurProfilPK> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UtilisateurProfilPK>) => response.ok),
                map((utilisateurProfilPK: HttpResponse<UtilisateurProfilPK>) => utilisateurProfilPK.body)
            );
        }
        return of(new UtilisateurProfilPK());
    }
}

export const utilisateurProfilPKRoute: Routes = [
    {
        path: 'utilisateur-profil-pk',
        component: UtilisateurProfilPKComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.utilisateurProfilPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-profil-pk/:id/view',
        component: UtilisateurProfilPKDetailComponent,
        resolve: {
            utilisateurProfilPK: UtilisateurProfilPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfilPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-profil-pk/new',
        component: UtilisateurProfilPKUpdateComponent,
        resolve: {
            utilisateurProfilPK: UtilisateurProfilPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfilPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-profil-pk/:id/edit',
        component: UtilisateurProfilPKUpdateComponent,
        resolve: {
            utilisateurProfilPK: UtilisateurProfilPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfilPK.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utilisateurProfilPKPopupRoute: Routes = [
    {
        path: 'utilisateur-profil-pk/:id/delete',
        component: UtilisateurProfilPKDeletePopupComponent,
        resolve: {
            utilisateurProfilPK: UtilisateurProfilPKResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfilPK.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
