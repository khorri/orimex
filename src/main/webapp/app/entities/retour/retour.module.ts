import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    RetourComponent,
    RetourDetailComponent,
    RetourUpdateComponent,
    RetourDeletePopupComponent,
    RetourDeleteDialogComponent,
    retourRoute,
    retourPopupRoute
} from './';

const ENTITY_STATES = [...retourRoute, ...retourPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [RetourComponent, RetourDetailComponent, RetourUpdateComponent, RetourDeleteDialogComponent, RetourDeletePopupComponent],
    entryComponents: [RetourComponent, RetourUpdateComponent, RetourDeleteDialogComponent, RetourDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexRetourModule {}
