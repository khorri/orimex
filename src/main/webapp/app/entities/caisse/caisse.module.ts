import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    CaisseComponent,
    CaisseDetailComponent,
    CaisseUpdateComponent,
    CaisseDeletePopupComponent,
    CaisseDeleteDialogComponent,
    caisseRoute,
    caissePopupRoute
} from './';

const ENTITY_STATES = [...caisseRoute, ...caissePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CaisseComponent, CaisseDetailComponent, CaisseUpdateComponent, CaisseDeleteDialogComponent, CaisseDeletePopupComponent],
    entryComponents: [CaisseComponent, CaisseUpdateComponent, CaisseDeleteDialogComponent, CaisseDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexCaisseModule {}
