import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    ConteneurComponent,
    ConteneurDetailComponent,
    ConteneurUpdateComponent,
    ConteneurDeletePopupComponent,
    ConteneurDeleteDialogComponent,
    conteneurRoute,
    conteneurPopupRoute
} from './';

const ENTITY_STATES = [...conteneurRoute, ...conteneurPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ConteneurComponent,
        ConteneurDetailComponent,
        ConteneurUpdateComponent,
        ConteneurDeleteDialogComponent,
        ConteneurDeletePopupComponent
    ],
    entryComponents: [ConteneurComponent, ConteneurUpdateComponent, ConteneurDeleteDialogComponent, ConteneurDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexConteneurModule {}
