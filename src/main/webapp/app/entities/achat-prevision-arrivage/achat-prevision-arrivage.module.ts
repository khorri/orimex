import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatPrevisionArrivageComponent,
    AchatPrevisionArrivageDetailComponent,
    AchatPrevisionArrivageUpdateComponent,
    AchatPrevisionArrivageDeletePopupComponent,
    AchatPrevisionArrivageDeleteDialogComponent,
    achatPrevisionArrivageRoute,
    achatPrevisionArrivagePopupRoute
} from './';

const ENTITY_STATES = [...achatPrevisionArrivageRoute, ...achatPrevisionArrivagePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatPrevisionArrivageComponent,
        AchatPrevisionArrivageDetailComponent,
        AchatPrevisionArrivageUpdateComponent,
        AchatPrevisionArrivageDeleteDialogComponent,
        AchatPrevisionArrivageDeletePopupComponent
    ],
    entryComponents: [
        AchatPrevisionArrivageComponent,
        AchatPrevisionArrivageUpdateComponent,
        AchatPrevisionArrivageDeleteDialogComponent,
        AchatPrevisionArrivageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatPrevisionArrivageModule {}
