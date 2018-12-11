import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    OrigineComponent,
    OrigineDetailComponent,
    OrigineUpdateComponent,
    OrigineDeletePopupComponent,
    OrigineDeleteDialogComponent,
    origineRoute,
    originePopupRoute
} from './';

const ENTITY_STATES = [...origineRoute, ...originePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        OrigineComponent,
        OrigineDetailComponent,
        OrigineUpdateComponent,
        OrigineDeleteDialogComponent,
        OrigineDeletePopupComponent
    ],
    entryComponents: [OrigineComponent, OrigineUpdateComponent, OrigineDeleteDialogComponent, OrigineDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexOrigineModule {}
