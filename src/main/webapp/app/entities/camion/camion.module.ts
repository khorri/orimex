import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    CamionComponent,
    CamionDetailComponent,
    CamionUpdateComponent,
    CamionDeletePopupComponent,
    CamionDeleteDialogComponent,
    camionRoute,
    camionPopupRoute
} from './';

const ENTITY_STATES = [...camionRoute, ...camionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CamionComponent, CamionDetailComponent, CamionUpdateComponent, CamionDeleteDialogComponent, CamionDeletePopupComponent],
    entryComponents: [CamionComponent, CamionUpdateComponent, CamionDeleteDialogComponent, CamionDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexCamionModule {}
