import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatLigneProformaComponent,
    AchatLigneProformaDetailComponent,
    AchatLigneProformaUpdateComponent,
    AchatLigneProformaDeletePopupComponent,
    AchatLigneProformaDeleteDialogComponent,
    achatLigneProformaRoute,
    achatLigneProformaPopupRoute
} from './';

const ENTITY_STATES = [...achatLigneProformaRoute, ...achatLigneProformaPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatLigneProformaComponent,
        AchatLigneProformaDetailComponent,
        AchatLigneProformaUpdateComponent,
        AchatLigneProformaDeleteDialogComponent,
        AchatLigneProformaDeletePopupComponent
    ],
    entryComponents: [
        AchatLigneProformaComponent,
        AchatLigneProformaUpdateComponent,
        AchatLigneProformaDeleteDialogComponent,
        AchatLigneProformaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatLigneProformaModule {}
