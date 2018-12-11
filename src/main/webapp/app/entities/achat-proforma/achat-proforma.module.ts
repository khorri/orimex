import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatProformaComponent,
    AchatProformaDetailComponent,
    AchatProformaUpdateComponent,
    AchatProformaDeletePopupComponent,
    AchatProformaDeleteDialogComponent,
    achatProformaRoute,
    achatProformaPopupRoute
} from './';

const ENTITY_STATES = [...achatProformaRoute, ...achatProformaPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatProformaComponent,
        AchatProformaDetailComponent,
        AchatProformaUpdateComponent,
        AchatProformaDeleteDialogComponent,
        AchatProformaDeletePopupComponent
    ],
    entryComponents: [
        AchatProformaComponent,
        AchatProformaUpdateComponent,
        AchatProformaDeleteDialogComponent,
        AchatProformaDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatProformaModule {}
