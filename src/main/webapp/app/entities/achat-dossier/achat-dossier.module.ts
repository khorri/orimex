import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatDossierComponent,
    AchatDossierDetailComponent,
    AchatDossierUpdateComponent,
    AchatDossierDeletePopupComponent,
    AchatDossierDeleteDialogComponent,
    achatDossierRoute,
    achatDossierPopupRoute
} from './';

const ENTITY_STATES = [...achatDossierRoute, ...achatDossierPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatDossierComponent,
        AchatDossierDetailComponent,
        AchatDossierUpdateComponent,
        AchatDossierDeleteDialogComponent,
        AchatDossierDeletePopupComponent
    ],
    entryComponents: [
        AchatDossierComponent,
        AchatDossierUpdateComponent,
        AchatDossierDeleteDialogComponent,
        AchatDossierDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatDossierModule {}
