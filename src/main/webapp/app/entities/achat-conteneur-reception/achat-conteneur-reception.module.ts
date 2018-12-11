import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatConteneurReceptionComponent,
    AchatConteneurReceptionDetailComponent,
    AchatConteneurReceptionUpdateComponent,
    AchatConteneurReceptionDeletePopupComponent,
    AchatConteneurReceptionDeleteDialogComponent,
    achatConteneurReceptionRoute,
    achatConteneurReceptionPopupRoute
} from './';

const ENTITY_STATES = [...achatConteneurReceptionRoute, ...achatConteneurReceptionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatConteneurReceptionComponent,
        AchatConteneurReceptionDetailComponent,
        AchatConteneurReceptionUpdateComponent,
        AchatConteneurReceptionDeleteDialogComponent,
        AchatConteneurReceptionDeletePopupComponent
    ],
    entryComponents: [
        AchatConteneurReceptionComponent,
        AchatConteneurReceptionUpdateComponent,
        AchatConteneurReceptionDeleteDialogComponent,
        AchatConteneurReceptionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatConteneurReceptionModule {}
