import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    ProfilActionPKComponent,
    ProfilActionPKDetailComponent,
    ProfilActionPKUpdateComponent,
    ProfilActionPKDeletePopupComponent,
    ProfilActionPKDeleteDialogComponent,
    profilActionPKRoute,
    profilActionPKPopupRoute
} from './';

const ENTITY_STATES = [...profilActionPKRoute, ...profilActionPKPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ProfilActionPKComponent,
        ProfilActionPKDetailComponent,
        ProfilActionPKUpdateComponent,
        ProfilActionPKDeleteDialogComponent,
        ProfilActionPKDeletePopupComponent
    ],
    entryComponents: [
        ProfilActionPKComponent,
        ProfilActionPKUpdateComponent,
        ProfilActionPKDeleteDialogComponent,
        ProfilActionPKDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexProfilActionPKModule {}
