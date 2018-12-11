import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatFournisseurComponent,
    AchatFournisseurDetailComponent,
    AchatFournisseurUpdateComponent,
    AchatFournisseurDeletePopupComponent,
    AchatFournisseurDeleteDialogComponent,
    achatFournisseurRoute,
    achatFournisseurPopupRoute
} from './';

const ENTITY_STATES = [...achatFournisseurRoute, ...achatFournisseurPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatFournisseurComponent,
        AchatFournisseurDetailComponent,
        AchatFournisseurUpdateComponent,
        AchatFournisseurDeleteDialogComponent,
        AchatFournisseurDeletePopupComponent
    ],
    entryComponents: [
        AchatFournisseurComponent,
        AchatFournisseurUpdateComponent,
        AchatFournisseurDeleteDialogComponent,
        AchatFournisseurDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatFournisseurModule {}
