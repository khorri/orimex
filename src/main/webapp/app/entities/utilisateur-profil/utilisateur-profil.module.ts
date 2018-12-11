import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    UtilisateurProfilComponent,
    UtilisateurProfilDetailComponent,
    UtilisateurProfilUpdateComponent,
    UtilisateurProfilDeletePopupComponent,
    UtilisateurProfilDeleteDialogComponent,
    utilisateurProfilRoute,
    utilisateurProfilPopupRoute
} from './';

const ENTITY_STATES = [...utilisateurProfilRoute, ...utilisateurProfilPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtilisateurProfilComponent,
        UtilisateurProfilDetailComponent,
        UtilisateurProfilUpdateComponent,
        UtilisateurProfilDeleteDialogComponent,
        UtilisateurProfilDeletePopupComponent
    ],
    entryComponents: [
        UtilisateurProfilComponent,
        UtilisateurProfilUpdateComponent,
        UtilisateurProfilDeleteDialogComponent,
        UtilisateurProfilDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexUtilisateurProfilModule {}
