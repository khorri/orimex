import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StockReception } from 'app/shared/model/stock-reception.model';
import { StockReceptionService } from './stock-reception.service';
import { StockReceptionComponent } from './stock-reception.component';
import { StockReceptionDetailComponent } from './stock-reception-detail.component';
import { StockReceptionUpdateComponent } from './stock-reception-update.component';
import { StockReceptionDeletePopupComponent } from './stock-reception-delete-dialog.component';
import { IStockReception } from 'app/shared/model/stock-reception.model';

@Injectable({ providedIn: 'root' })
export class StockReceptionResolve implements Resolve<IStockReception> {
    constructor(private service: StockReceptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StockReception> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StockReception>) => response.ok),
                map((stockReception: HttpResponse<StockReception>) => stockReception.body)
            );
        }
        return of(new StockReception());
    }
}

export const stockReceptionRoute: Routes = [
    {
        path: 'stock-reception',
        component: StockReceptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.stockReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-reception/:id/view',
        component: StockReceptionDetailComponent,
        resolve: {
            stockReception: StockReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-reception/new',
        component: StockReceptionUpdateComponent,
        resolve: {
            stockReception: StockReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-reception/:id/edit',
        component: StockReceptionUpdateComponent,
        resolve: {
            stockReception: StockReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stockReceptionPopupRoute: Routes = [
    {
        path: 'stock-reception/:id/delete',
        component: StockReceptionDeletePopupComponent,
        resolve: {
            stockReception: StockReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockReception.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
