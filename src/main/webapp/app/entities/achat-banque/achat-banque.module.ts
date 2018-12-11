import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatBanqueComponent,
    AchatBanqueDetailComponent,
    AchatBanqueUpdateComponent,
    AchatBanqueDeletePopupComponent,
    AchatBanqueDeleteDialogComponent,
    achatBanqueRoute,
    achatBanquePopupRoute
} from './';

const ENTITY_STATES = [...achatBanqueRoute, ...achatBanquePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatBanqueComponent,
        AchatBanqueDetailComponent,
        AchatBanqueUpdateComponent,
        AchatBanqueDeleteDialogComponent,
        AchatBanqueDeletePopupComponent
    ],
    entryComponents: [AchatBanqueComponent, AchatBanqueUpdateComponent, AchatBanqueDeleteDialogComponent, AchatBanqueDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatBanqueModule {}
