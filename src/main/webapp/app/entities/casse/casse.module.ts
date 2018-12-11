import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    CasseComponent,
    CasseDetailComponent,
    CasseUpdateComponent,
    CasseDeletePopupComponent,
    CasseDeleteDialogComponent,
    casseRoute,
    cassePopupRoute
} from './';

const ENTITY_STATES = [...casseRoute, ...cassePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CasseComponent, CasseDetailComponent, CasseUpdateComponent, CasseDeleteDialogComponent, CasseDeletePopupComponent],
    entryComponents: [CasseComponent, CasseUpdateComponent, CasseDeleteDialogComponent, CasseDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexCasseModule {}
