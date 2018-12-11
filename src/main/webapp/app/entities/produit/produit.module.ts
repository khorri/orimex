import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    ProduitComponent,
    ProduitDetailComponent,
    ProduitUpdateComponent,
    ProduitDeletePopupComponent,
    ProduitDeleteDialogComponent,
    produitRoute,
    produitPopupRoute
} from './';

const ENTITY_STATES = [...produitRoute, ...produitPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProduitComponent,
        ProduitDetailComponent,
        ProduitUpdateComponent,
        ProduitDeleteDialogComponent,
        ProduitDeletePopupComponent
    ],
    entryComponents: [ProduitComponent, ProduitUpdateComponent, ProduitDeleteDialogComponent, ProduitDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexProduitModule {}
