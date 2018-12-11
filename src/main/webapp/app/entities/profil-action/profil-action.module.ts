import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    ProfilActionComponent,
    ProfilActionDetailComponent,
    ProfilActionUpdateComponent,
    ProfilActionDeletePopupComponent,
    ProfilActionDeleteDialogComponent,
    profilActionRoute,
    profilActionPopupRoute
} from './';

const ENTITY_STATES = [...profilActionRoute, ...profilActionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfilActionComponent,
        ProfilActionDetailComponent,
        ProfilActionUpdateComponent,
        ProfilActionDeleteDialogComponent,
        ProfilActionDeletePopupComponent
    ],
    entryComponents: [
        ProfilActionComponent,
        ProfilActionUpdateComponent,
        ProfilActionDeleteDialogComponent,
        ProfilActionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexProfilActionModule {}
