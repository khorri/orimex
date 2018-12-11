import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Sortie } from 'app/shared/model/sortie.model';
import { SortieService } from './sortie.service';
import { SortieComponent } from './sortie.component';
import { SortieDetailComponent } from './sortie-detail.component';
import { SortieUpdateComponent } from './sortie-update.component';
import { SortieDeletePopupComponent } from './sortie-delete-dialog.component';
import { ISortie } from 'app/shared/model/sortie.model';

@Injectable({ providedIn: 'root' })
export class SortieResolve implements Resolve<ISortie> {
    constructor(private service: SortieService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Sortie> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Sortie>) => response.ok),
                map((sortie: HttpResponse<Sortie>) => sortie.body)
            );
        }
        return of(new Sortie());
    }
}

export const sortieRoute: Routes = [
    {
        path: 'sortie',
        component: SortieComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.sortie.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sortie/:id/view',
        component: SortieDetailComponent,
        resolve: {
            sortie: SortieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.sortie.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sortie/new',
        component: SortieUpdateComponent,
        resolve: {
            sortie: SortieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.sortie.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'sortie/:id/edit',
        component: SortieUpdateComponent,
        resolve: {
            sortie: SortieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.sortie.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sortiePopupRoute: Routes = [
    {
        path: 'sortie/:id/delete',
        component: SortieDeletePopupComponent,
        resolve: {
            sortie: SortieResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.sortie.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
