import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    CouleurComponent,
    CouleurDetailComponent,
    CouleurUpdateComponent,
    CouleurDeletePopupComponent,
    CouleurDeleteDialogComponent,
    couleurRoute,
    couleurPopupRoute
} from './';

const ENTITY_STATES = [...couleurRoute, ...couleurPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        CouleurComponent,
        CouleurDetailComponent,
        CouleurUpdateComponent,
        CouleurDeleteDialogComponent,
        CouleurDeletePopupComponent
    ],
    entryComponents: [CouleurComponent, CouleurUpdateComponent, CouleurDeleteDialogComponent, CouleurDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexCouleurModule {}
