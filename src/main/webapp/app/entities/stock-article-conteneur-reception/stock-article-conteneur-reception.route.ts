import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';
import { StockArticleConteneurReceptionService } from './stock-article-conteneur-reception.service';
import { StockArticleConteneurReceptionComponent } from './stock-article-conteneur-reception.component';
import { StockArticleConteneurReceptionDetailComponent } from './stock-article-conteneur-reception-detail.component';
import { StockArticleConteneurReceptionUpdateComponent } from './stock-article-conteneur-reception-update.component';
import { StockArticleConteneurReceptionDeletePopupComponent } from './stock-article-conteneur-reception-delete-dialog.component';
import { IStockArticleConteneurReception } from 'app/shared/model/stock-article-conteneur-reception.model';

@Injectable({ providedIn: 'root' })
export class StockArticleConteneurReceptionResolve implements Resolve<IStockArticleConteneurReception> {
    constructor(private service: StockArticleConteneurReceptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<StockArticleConteneurReception> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<StockArticleConteneurReception>) => response.ok),
                map((stockArticleConteneurReception: HttpResponse<StockArticleConteneurReception>) => stockArticleConteneurReception.body)
            );
        }
        return of(new StockArticleConteneurReception());
    }
}

export const stockArticleConteneurReceptionRoute: Routes = [
    {
        path: 'stock-article-conteneur-reception',
        component: StockArticleConteneurReceptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.stockArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-article-conteneur-reception/:id/view',
        component: StockArticleConteneurReceptionDetailComponent,
        resolve: {
            stockArticleConteneurReception: StockArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-article-conteneur-reception/new',
        component: StockArticleConteneurReceptionUpdateComponent,
        resolve: {
            stockArticleConteneurReception: StockArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'stock-article-conteneur-reception/:id/edit',
        component: StockArticleConteneurReceptionUpdateComponent,
        resolve: {
            stockArticleConteneurReception: StockArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stockArticleConteneurReceptionPopupRoute: Routes = [
    {
        path: 'stock-article-conteneur-reception/:id/delete',
        component: StockArticleConteneurReceptionDeletePopupComponent,
        resolve: {
            stockArticleConteneurReception: StockArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.stockArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
