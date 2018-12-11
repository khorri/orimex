import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';
import { AchatArticleConteneurReceptionService } from './achat-article-conteneur-reception.service';
import { AchatArticleConteneurReceptionComponent } from './achat-article-conteneur-reception.component';
import { AchatArticleConteneurReceptionDetailComponent } from './achat-article-conteneur-reception-detail.component';
import { AchatArticleConteneurReceptionUpdateComponent } from './achat-article-conteneur-reception-update.component';
import { AchatArticleConteneurReceptionDeletePopupComponent } from './achat-article-conteneur-reception-delete-dialog.component';
import { IAchatArticleConteneurReception } from 'app/shared/model/achat-article-conteneur-reception.model';

@Injectable({ providedIn: 'root' })
export class AchatArticleConteneurReceptionResolve implements Resolve<IAchatArticleConteneurReception> {
    constructor(private service: AchatArticleConteneurReceptionService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatArticleConteneurReception> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatArticleConteneurReception>) => response.ok),
                map((achatArticleConteneurReception: HttpResponse<AchatArticleConteneurReception>) => achatArticleConteneurReception.body)
            );
        }
        return of(new AchatArticleConteneurReception());
    }
}

export const achatArticleConteneurReceptionRoute: Routes = [
    {
        path: 'achat-article-conteneur-reception',
        component: AchatArticleConteneurReceptionComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-conteneur-reception/:id/view',
        component: AchatArticleConteneurReceptionDetailComponent,
        resolve: {
            achatArticleConteneurReception: AchatArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-conteneur-reception/new',
        component: AchatArticleConteneurReceptionUpdateComponent,
        resolve: {
            achatArticleConteneurReception: AchatArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-conteneur-reception/:id/edit',
        component: AchatArticleConteneurReceptionUpdateComponent,
        resolve: {
            achatArticleConteneurReception: AchatArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatArticleConteneurReceptionPopupRoute: Routes = [
    {
        path: 'achat-article-conteneur-reception/:id/delete',
        component: AchatArticleConteneurReceptionDeletePopupComponent,
        resolve: {
            achatArticleConteneurReception: AchatArticleConteneurReceptionResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurReception.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
