import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Conteneur } from 'app/shared/model/conteneur.model';
import { ConteneurService } from './conteneur.service';
import { ConteneurComponent } from './conteneur.component';
import { ConteneurDetailComponent } from './conteneur-detail.component';
import { ConteneurUpdateComponent } from './conteneur-update.component';
import { ConteneurDeletePopupComponent } from './conteneur-delete-dialog.component';
import { IConteneur } from 'app/shared/model/conteneur.model';

@Injectable({ providedIn: 'root' })
export class ConteneurResolve implements Resolve<IConteneur> {
    constructor(private service: ConteneurService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Conteneur> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Conteneur>) => response.ok),
                map((conteneur: HttpResponse<Conteneur>) => conteneur.body)
            );
        }
        return of(new Conteneur());
    }
}

export const conteneurRoute: Routes = [
    {
        path: 'conteneur',
        component: ConteneurComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.conteneur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conteneur/:id/view',
        component: ConteneurDetailComponent,
        resolve: {
            conteneur: ConteneurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.conteneur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conteneur/new',
        component: ConteneurUpdateComponent,
        resolve: {
            conteneur: ConteneurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.conteneur.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'conteneur/:id/edit',
        component: ConteneurUpdateComponent,
        resolve: {
            conteneur: ConteneurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.conteneur.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const conteneurPopupRoute: Routes = [
    {
        path: 'conteneur/:id/delete',
        component: ConteneurDeletePopupComponent,
        resolve: {
            conteneur: ConteneurResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.conteneur.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
