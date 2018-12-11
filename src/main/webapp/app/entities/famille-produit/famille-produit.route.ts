import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { FamilleProduit } from 'app/shared/model/famille-produit.model';
import { FamilleProduitService } from './famille-produit.service';
import { FamilleProduitComponent } from './famille-produit.component';
import { FamilleProduitDetailComponent } from './famille-produit-detail.component';
import { FamilleProduitUpdateComponent } from './famille-produit-update.component';
import { FamilleProduitDeletePopupComponent } from './famille-produit-delete-dialog.component';
import { IFamilleProduit } from 'app/shared/model/famille-produit.model';

@Injectable({ providedIn: 'root' })
export class FamilleProduitResolve implements Resolve<IFamilleProduit> {
    constructor(private service: FamilleProduitService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FamilleProduit> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FamilleProduit>) => response.ok),
                map((familleProduit: HttpResponse<FamilleProduit>) => familleProduit.body)
            );
        }
        return of(new FamilleProduit());
    }
}

export const familleProduitRoute: Routes = [
    {
        path: 'famille-produit',
        component: FamilleProduitComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'orimexApp.familleProduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'famille-produit/:id/view',
        component: FamilleProduitDetailComponent,
        resolve: {
            familleProduit: FamilleProduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.familleProduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'famille-produit/new',
        component: FamilleProduitUpdateComponent,
        resolve: {
            familleProduit: FamilleProduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.familleProduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'famille-produit/:id/edit',
        component: FamilleProduitUpdateComponent,
        resolve: {
            familleProduit: FamilleProduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.familleProduit.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const familleProduitPopupRoute: Routes = [
    {
        path: 'famille-produit/:id/delete',
        component: FamilleProduitDeletePopupComponent,
        resolve: {
            familleProduit: FamilleProduitResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'orimexApp.familleProduit.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
