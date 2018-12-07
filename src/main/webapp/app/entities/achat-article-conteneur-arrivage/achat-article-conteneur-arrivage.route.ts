import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';
import { AchatArticleConteneurArrivageService } from './achat-article-conteneur-arrivage.service';
import { AchatArticleConteneurArrivageComponent } from './achat-article-conteneur-arrivage.component';
import { AchatArticleConteneurArrivageDetailComponent } from './achat-article-conteneur-arrivage-detail.component';
import { AchatArticleConteneurArrivageUpdateComponent } from './achat-article-conteneur-arrivage-update.component';
import { AchatArticleConteneurArrivageDeletePopupComponent } from './achat-article-conteneur-arrivage-delete-dialog.component';
import { IAchatArticleConteneurArrivage } from 'app/shared/model/achat-article-conteneur-arrivage.model';

@Injectable({ providedIn: 'root' })
export class AchatArticleConteneurArrivageResolve implements Resolve<IAchatArticleConteneurArrivage> {
    constructor(private service: AchatArticleConteneurArrivageService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<AchatArticleConteneurArrivage> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AchatArticleConteneurArrivage>) => response.ok),
                map((achatArticleConteneurArrivage: HttpResponse<AchatArticleConteneurArrivage>) => achatArticleConteneurArrivage.body)
            );
        }
        return of(new AchatArticleConteneurArrivage());
    }
}

export const achatArticleConteneurArrivageRoute: Routes = [
    {
        path: 'achat-article-conteneur-arrivage',
        component: AchatArticleConteneurArrivageComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.achatArticleConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-conteneur-arrivage/:id/view',
        component: AchatArticleConteneurArrivageDetailComponent,
        resolve: {
            achatArticleConteneurArrivage: AchatArticleConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-conteneur-arrivage/new',
        component: AchatArticleConteneurArrivageUpdateComponent,
        resolve: {
            achatArticleConteneurArrivage: AchatArticleConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'achat-article-conteneur-arrivage/:id/edit',
        component: AchatArticleConteneurArrivageUpdateComponent,
        resolve: {
            achatArticleConteneurArrivage: AchatArticleConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const achatArticleConteneurArrivagePopupRoute: Routes = [
    {
        path: 'achat-article-conteneur-arrivage/:id/delete',
        component: AchatArticleConteneurArrivageDeletePopupComponent,
        resolve: {
            achatArticleConteneurArrivage: AchatArticleConteneurArrivageResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.achatArticleConteneurArrivage.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
