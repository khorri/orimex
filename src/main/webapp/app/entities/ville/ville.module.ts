import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    VilleComponent,
    VilleDetailComponent,
    VilleUpdateComponent,
    VilleDeletePopupComponent,
    VilleDeleteDialogComponent,
    villeRoute,
    villePopupRoute
} from './';

const ENTITY_STATES = [...villeRoute, ...villePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [VilleComponent, VilleDetailComponent, VilleUpdateComponent, VilleDeleteDialogComponent, VilleDeletePopupComponent],
    entryComponents: [VilleComponent, VilleUpdateComponent, VilleDeleteDialogComponent, VilleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexVilleModule {}
