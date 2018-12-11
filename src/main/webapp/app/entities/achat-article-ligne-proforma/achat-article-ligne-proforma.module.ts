import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatArticleLigneProformaComponent,
    AchatArticleLigneProformaDetailComponent,
    AchatArticleLigneProformaUpdateComponent,
    AchatArticleLigneProformaDeletePopupComponent,
    AchatArticleLigneProformaDeleteDialogComponent,
    achatArticleLigneProformaRoute,
    achatArticleLigneProformaPopupRoute
} from './';

const ENTITY_STATES = [...achatArticleLigneProformaRoute, ...achatArticleLigneProformaPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatArticleLigneProformaComponent,
        AchatArticleLigneProformaDetailComponent,
        AchatArticleLigneProformaUpdateComponent,
        AchatArticleLigneProformaDeleteDialogComponent,
        AchatArticleLigneProformaDeletePopupComponent
    ],
    entryComponents: [
        AchatArticleLigneProformaComponent,
        AchatArticleLigneProformaUpdateComponent,
        AchatArticleLigneProformaDeleteDialogComponent,
        AchatArticleLigneProformaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatArticleLigneProformaModule {}
