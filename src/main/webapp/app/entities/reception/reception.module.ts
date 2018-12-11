import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    ReceptionComponent,
    ReceptionDetailComponent,
    ReceptionUpdateComponent,
    ReceptionDeletePopupComponent,
    ReceptionDeleteDialogComponent,
    receptionRoute,
    receptionPopupRoute
} from './';

const ENTITY_STATES = [...receptionRoute, ...receptionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ReceptionComponent,
        ReceptionDetailComponent,
        ReceptionUpdateComponent,
        ReceptionDeleteDialogComponent,
        ReceptionDeletePopupComponent
    ],
    entryComponents: [ReceptionComponent, ReceptionUpdateComponent, ReceptionDeleteDialogComponent, ReceptionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexReceptionModule {}
