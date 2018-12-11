import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatStatutDossierComponent,
    AchatStatutDossierDetailComponent,
    AchatStatutDossierUpdateComponent,
    AchatStatutDossierDeletePopupComponent,
    AchatStatutDossierDeleteDialogComponent,
    achatStatutDossierRoute,
    achatStatutDossierPopupRoute
} from './';

const ENTITY_STATES = [...achatStatutDossierRoute, ...achatStatutDossierPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatStatutDossierComponent,
        AchatStatutDossierDetailComponent,
        AchatStatutDossierUpdateComponent,
        AchatStatutDossierDeleteDialogComponent,
        AchatStatutDossierDeletePopupComponent
    ],
    entryComponents: [
        AchatStatutDossierComponent,
        AchatStatutDossierUpdateComponent,
        AchatStatutDossierDeleteDialogComponent,
        AchatStatutDossierDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatStatutDossierModule {}
