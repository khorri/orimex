import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    FamilleProduitComponent,
    FamilleProduitDetailComponent,
    FamilleProduitUpdateComponent,
    FamilleProduitDeletePopupComponent,
    FamilleProduitDeleteDialogComponent,
    familleProduitRoute,
    familleProduitPopupRoute
} from './';

const ENTITY_STATES = [...familleProduitRoute, ...familleProduitPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        FamilleProduitComponent,
        FamilleProduitDetailComponent,
        FamilleProduitUpdateComponent,
        FamilleProduitDeleteDialogComponent,
        FamilleProduitDeletePopupComponent
    ],
    entryComponents: [
        FamilleProduitComponent,
        FamilleProduitUpdateComponent,
        FamilleProduitDeleteDialogComponent,
        FamilleProduitDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexFamilleProduitModule {}
