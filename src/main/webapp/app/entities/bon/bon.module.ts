import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    BonComponent,
    BonDetailComponent,
    BonUpdateComponent,
    BonDeletePopupComponent,
    BonDeleteDialogComponent,
    bonRoute,
    bonPopupRoute
} from './';

const ENTITY_STATES = [...bonRoute, ...bonPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [BonComponent, BonDetailComponent, BonUpdateComponent, BonDeleteDialogComponent, BonDeletePopupComponent],
    entryComponents: [BonComponent, BonUpdateComponent, BonDeleteDialogComponent, BonDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexBonModule {}
