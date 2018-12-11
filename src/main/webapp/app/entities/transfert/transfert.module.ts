import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    TransfertComponent,
    TransfertDetailComponent,
    TransfertUpdateComponent,
    TransfertDeletePopupComponent,
    TransfertDeleteDialogComponent,
    transfertRoute,
    transfertPopupRoute
} from './';

const ENTITY_STATES = [...transfertRoute, ...transfertPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TransfertComponent,
        TransfertDetailComponent,
        TransfertUpdateComponent,
        TransfertDeleteDialogComponent,
        TransfertDeletePopupComponent
    ],
    entryComponents: [TransfertComponent, TransfertUpdateComponent, TransfertDeleteDialogComponent, TransfertDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexTransfertModule {}
