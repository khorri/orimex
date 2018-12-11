import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';
import { UtilisateurProfilService } from './utilisateur-profil.service';
import { UtilisateurProfilComponent } from './utilisateur-profil.component';
import { UtilisateurProfilDetailComponent } from './utilisateur-profil-detail.component';
import { UtilisateurProfilUpdateComponent } from './utilisateur-profil-update.component';
import { UtilisateurProfilDeletePopupComponent } from './utilisateur-profil-delete-dialog.component';
import { IUtilisateurProfil } from 'app/shared/model/utilisateur-profil.model';

@Injectable({ providedIn: 'root' })
export class UtilisateurProfilResolve implements Resolve<IUtilisateurProfil> {
    constructor(private service: UtilisateurProfilService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UtilisateurProfil> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UtilisateurProfil>) => response.ok),
                map((utilisateurProfil: HttpResponse<UtilisateurProfil>) => utilisateurProfil.body)
            );
        }
        return of(new UtilisateurProfil());
    }
}

export const utilisateurProfilRoute: Routes = [
    {
        path: 'utilisateur-profil',
        component: UtilisateurProfilComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.utilisateurProfil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-profil/:id/view',
        component: UtilisateurProfilDetailComponent,
        resolve: {
            utilisateurProfil: UtilisateurProfilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-profil/new',
        component: UtilisateurProfilUpdateComponent,
        resolve: {
            utilisateurProfil: UtilisateurProfilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfil.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-profil/:id/edit',
        component: UtilisateurProfilUpdateComponent,
        resolve: {
            utilisateurProfil: UtilisateurProfilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfil.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utilisateurProfilPopupRoute: Routes = [
    {
        path: 'utilisateur-profil/:id/delete',
        component: UtilisateurProfilDeletePopupComponent,
        resolve: {
            utilisateurProfil: UtilisateurProfilResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurProfil.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
