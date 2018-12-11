import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';
import { StockConteneurReceptionService } from './stock-conteneur-reception.service';
import { StockConteneurReceptionComponent } from './stock-conteneur-reception.component';
import { StockConteneurReceptionDetailComponent } from './stock-conteneur-reception-detail.component';
import { StockConteneurReceptionUpdateComponent } from './stock-conteneur-reception-update.component';
import { StockConteneurReceptionDeletePopupComponent } from './stock-conteneur-reception-delete-dialog.component';
import { IStockConteneurReception } from 'app/shared/model/stock-conteneur-reception.model';

@Injectable({ providedIn: 'root' })
export class StockConteneurReceptionResolve implements Resolve<IStockConteneurReception> {
    constructor(private service: StockConteneurReceptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StockConteneurReception> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StockConteneurReception>) => response.ok),
                map((stockConteneurReception: HttpResponse<StockConteneurReception>) => stockConteneurReception.body)
            );
        }
        return of(new StockConteneurReception());
    }
}

export const stockConteneurReceptionRoute: Routes = [
    {
        path: 'stock-conteneur-reception',
        component: StockConteneurReceptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.stockConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-conteneur-reception/:id/view',
        component: StockConteneurReceptionDetailComponent,
        resolve: {
            stockConteneurReception: StockConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-conteneur-reception/new',
        component: StockConteneurReceptionUpdateComponent,
        resolve: {
            stockConteneurReception: StockConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-conteneur-reception/:id/edit',
        component: StockConteneurReceptionUpdateComponent,
        resolve: {
            stockConteneurReception: StockConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stockConteneurReceptionPopupRoute: Routes = [
    {
        path: 'stock-conteneur-reception/:id/delete',
        component: StockConteneurReceptionDeletePopupComponent,
        resolve: {
            stockConteneurReception: StockConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
