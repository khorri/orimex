import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatArrivageComponent,
    AchatArrivageDetailComponent,
    AchatArrivageUpdateComponent,
    AchatArrivageDeletePopupComponent,
    AchatArrivageDeleteDialogComponent,
    achatArrivageRoute,
    achatArrivagePopupRoute
} from './';

const ENTITY_STATES = [...achatArrivageRoute, ...achatArrivagePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatArrivageComponent,
        AchatArrivageDetailComponent,
        AchatArrivageUpdateComponent,
        AchatArrivageDeleteDialogComponent,
        AchatArrivageDeletePopupComponent
    ],
    entryComponents: [
        AchatArrivageComponent,
        AchatArrivageUpdateComponent,
        AchatArrivageDeleteDialogComponent,
        AchatArrivageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatArrivageModule {}
