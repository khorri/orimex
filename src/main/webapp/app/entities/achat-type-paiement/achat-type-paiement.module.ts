import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatTypePaiementComponent,
    AchatTypePaiementDetailComponent,
    AchatTypePaiementUpdateComponent,
    AchatTypePaiementDeletePopupComponent,
    AchatTypePaiementDeleteDialogComponent,
    achatTypePaiementRoute,
    achatTypePaiementPopupRoute
} from './';

const ENTITY_STATES = [...achatTypePaiementRoute, ...achatTypePaiementPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatTypePaiementComponent,
        AchatTypePaiementDetailComponent,
        AchatTypePaiementUpdateComponent,
        AchatTypePaiementDeleteDialogComponent,
        AchatTypePaiementDeletePopupComponent
    ],
    entryComponents: [
        AchatTypePaiementComponent,
        AchatTypePaiementUpdateComponent,
        AchatTypePaiementDeleteDialogComponent,
        AchatTypePaiementDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatTypePaiementModule {}
