import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatFactureComponent,
    AchatFactureDetailComponent,
    AchatFactureUpdateComponent,
    AchatFactureDeletePopupComponent,
    AchatFactureDeleteDialogComponent,
    achatFactureRoute,
    achatFacturePopupRoute
} from './';

const ENTITY_STATES = [...achatFactureRoute, ...achatFacturePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatFactureComponent,
        AchatFactureDetailComponent,
        AchatFactureUpdateComponent,
        AchatFactureDeleteDialogComponent,
        AchatFactureDeletePopupComponent
    ],
    entryComponents: [
        AchatFactureComponent,
        AchatFactureUpdateComponent,
        AchatFactureDeleteDialogComponent,
        AchatFactureDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatFactureModule {}
