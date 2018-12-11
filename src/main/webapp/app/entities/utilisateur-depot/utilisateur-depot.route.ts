import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';
import { UtilisateurDepotService } from './utilisateur-depot.service';
import { UtilisateurDepotComponent } from './utilisateur-depot.component';
import { UtilisateurDepotDetailComponent } from './utilisateur-depot-detail.component';
import { UtilisateurDepotUpdateComponent } from './utilisateur-depot-update.component';
import { UtilisateurDepotDeletePopupComponent } from './utilisateur-depot-delete-dialog.component';
import { IUtilisateurDepot } from 'app/shared/model/utilisateur-depot.model';

@Injectable({ providedIn: 'root' })
export class UtilisateurDepotResolve implements Resolve<IUtilisateurDepot> {
    constructor(private service: UtilisateurDepotService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<UtilisateurDepot> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<UtilisateurDepot>) => response.ok),
                map((utilisateurDepot: HttpResponse<UtilisateurDepot>) => utilisateurDepot.body)
            );
        }
        return of(new UtilisateurDepot());
    }
}

export const utilisateurDepotRoute: Routes = [
    {
        path: 'utilisateur-depot',
        component: UtilisateurDepotComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.utilisateurDepot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-depot/:id/view',
        component: UtilisateurDepotDetailComponent,
        resolve: {
            utilisateurDepot: UtilisateurDepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-depot/new',
        component: UtilisateurDepotUpdateComponent,
        resolve: {
            utilisateurDepot: UtilisateurDepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'utilisateur-depot/:id/edit',
        component: UtilisateurDepotUpdateComponent,
        resolve: {
            utilisateurDepot: UtilisateurDepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepot.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const utilisateurDepotPopupRoute: Routes = [
    {
        path: 'utilisateur-depot/:id/delete',
        component: UtilisateurDepotDeletePopupComponent,
        resolve: {
            utilisateurDepot: UtilisateurDepotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.utilisateurDepot.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
