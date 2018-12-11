import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatDeviseComponent,
    AchatDeviseDetailComponent,
    AchatDeviseUpdateComponent,
    AchatDeviseDeletePopupComponent,
    AchatDeviseDeleteDialogComponent,
    achatDeviseRoute,
    achatDevisePopupRoute
} from './';

const ENTITY_STATES = [...achatDeviseRoute, ...achatDevisePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatDeviseComponent,
        AchatDeviseDetailComponent,
        AchatDeviseUpdateComponent,
        AchatDeviseDeleteDialogComponent,
        AchatDeviseDeletePopupComponent
    ],
    entryComponents: [AchatDeviseComponent, AchatDeviseUpdateComponent, AchatDeviseDeleteDialogComponent, AchatDeviseDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatDeviseModule {}
