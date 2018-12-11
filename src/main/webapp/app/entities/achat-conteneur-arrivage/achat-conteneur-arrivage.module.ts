import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatConteneurArrivageComponent,
    AchatConteneurArrivageDetailComponent,
    AchatConteneurArrivageUpdateComponent,
    AchatConteneurArrivageDeletePopupComponent,
    AchatConteneurArrivageDeleteDialogComponent,
    achatConteneurArrivageRoute,
    achatConteneurArrivagePopupRoute
} from './';

const ENTITY_STATES = [...achatConteneurArrivageRoute, ...achatConteneurArrivagePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatConteneurArrivageComponent,
        AchatConteneurArrivageDetailComponent,
        AchatConteneurArrivageUpdateComponent,
        AchatConteneurArrivageDeleteDialogComponent,
        AchatConteneurArrivageDeletePopupComponent
    ],
    entryComponents: [
        AchatConteneurArrivageComponent,
        AchatConteneurArrivageUpdateComponent,
        AchatConteneurArrivageDeleteDialogComponent,
        AchatConteneurArrivageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatConteneurArrivageModule {}
