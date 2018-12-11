import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    UtilisateurProfilPKComponent,
    UtilisateurProfilPKDetailComponent,
    UtilisateurProfilPKUpdateComponent,
    UtilisateurProfilPKDeletePopupComponent,
    UtilisateurProfilPKDeleteDialogComponent,
    utilisateurProfilPKRoute,
    utilisateurProfilPKPopupRoute
} from './';

const ENTITY_STATES = [...utilisateurProfilPKRoute, ...utilisateurProfilPKPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtilisateurProfilPKComponent,
        UtilisateurProfilPKDetailComponent,
        UtilisateurProfilPKUpdateComponent,
        UtilisateurProfilPKDeleteDialogComponent,
        UtilisateurProfilPKDeletePopupComponent
    ],
    entryComponents: [
        UtilisateurProfilPKComponent,
        UtilisateurProfilPKUpdateComponent,
        UtilisateurProfilPKDeleteDialogComponent,
        UtilisateurProfilPKDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexUtilisateurProfilPKModule {}
