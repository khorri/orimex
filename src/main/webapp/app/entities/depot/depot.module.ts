import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    DepotComponent,
    DepotDetailComponent,
    DepotUpdateComponent,
    DepotDeletePopupComponent,
    DepotDeleteDialogComponent,
    depotRoute,
    depotPopupRoute
} from './';

const ENTITY_STATES = [...depotRoute, ...depotPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [DepotComponent, DepotDetailComponent, DepotUpdateComponent, DepotDeleteDialogComponent, DepotDeletePopupComponent],
    entryComponents: [DepotComponent, DepotUpdateComponent, DepotDeleteDialogComponent, DepotDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexDepotModule {}
