import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Transfert } from 'app/shared/model/transfert.model';
import { TransfertService } from './transfert.service';
import { TransfertComponent } from './transfert.component';
import { TransfertDetailComponent } from './transfert-detail.component';
import { TransfertUpdateComponent } from './transfert-update.component';
import { TransfertDeletePopupComponent } from './transfert-delete-dialog.component';
import { ITransfert } from 'app/shared/model/transfert.model';

@Injectable({ providedIn: 'root' })
export class TransfertResolve implements Resolve<ITransfert> {
    constructor(private service: TransfertService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Transfert> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Transfert>) => response.ok),
                map((transfert: HttpResponse<Transfert>) => transfert.body)
            );
        }
        return of(new Transfert());
    }
}

export const transfertRoute: Routes = [
    {
        path: 'transfert',
        component: TransfertComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.transfert.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transfert/:id/view',
        component: TransfertDetailComponent,
        resolve: {
            transfert: TransfertResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.transfert.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transfert/new',
        component: TransfertUpdateComponent,
        resolve: {
            transfert: TransfertResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.transfert.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'transfert/:id/edit',
        component: TransfertUpdateComponent,
        resolve: {
            transfert: TransfertResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.transfert.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transfertPopupRoute: Routes = [
    {
        path: 'transfert/:id/delete',
        component: TransfertDeletePopupComponent,
        resolve: {
            transfert: TransfertResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.transfert.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
