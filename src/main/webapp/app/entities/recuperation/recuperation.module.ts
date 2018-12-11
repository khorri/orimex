import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    RecuperationComponent,
    RecuperationDetailComponent,
    RecuperationUpdateComponent,
    RecuperationDeletePopupComponent,
    RecuperationDeleteDialogComponent,
    recuperationRoute,
    recuperationPopupRoute
} from './';

const ENTITY_STATES = [...recuperationRoute, ...recuperationPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        RecuperationComponent,
        RecuperationDetailComponent,
        RecuperationUpdateComponent,
        RecuperationDeleteDialogComponent,
        RecuperationDeletePopupComponent
    ],
    entryComponents: [
        RecuperationComponent,
        RecuperationUpdateComponent,
        RecuperationDeleteDialogComponent,
        RecuperationDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexRecuperationModule {}
