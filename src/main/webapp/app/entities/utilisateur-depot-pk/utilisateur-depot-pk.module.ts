import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    UtilisateurDepotPKComponent,
    UtilisateurDepotPKDetailComponent,
    UtilisateurDepotPKUpdateComponent,
    UtilisateurDepotPKDeletePopupComponent,
    UtilisateurDepotPKDeleteDialogComponent,
    utilisateurDepotPKRoute,
    utilisateurDepotPKPopupRoute
} from './';

const ENTITY_STATES = [...utilisateurDepotPKRoute, ...utilisateurDepotPKPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtilisateurDepotPKComponent,
        UtilisateurDepotPKDetailComponent,
        UtilisateurDepotPKUpdateComponent,
        UtilisateurDepotPKDeleteDialogComponent,
        UtilisateurDepotPKDeletePopupComponent
    ],
    entryComponents: [
        UtilisateurDepotPKComponent,
        UtilisateurDepotPKUpdateComponent,
        UtilisateurDepotPKDeleteDialogComponent,
        UtilisateurDepotPKDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexUtilisateurDepotPKModule {}
