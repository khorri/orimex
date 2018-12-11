import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    UtilisateurDepotComponent,
    UtilisateurDepotDetailComponent,
    UtilisateurDepotUpdateComponent,
    UtilisateurDepotDeletePopupComponent,
    UtilisateurDepotDeleteDialogComponent,
    utilisateurDepotRoute,
    utilisateurDepotPopupRoute
} from './';

const ENTITY_STATES = [...utilisateurDepotRoute, ...utilisateurDepotPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UtilisateurDepotComponent,
        UtilisateurDepotDetailComponent,
        UtilisateurDepotUpdateComponent,
        UtilisateurDepotDeleteDialogComponent,
        UtilisateurDepotDeletePopupComponent
    ],
    entryComponents: [
        UtilisateurDepotComponent,
        UtilisateurDepotUpdateComponent,
        UtilisateurDepotDeleteDialogComponent,
        UtilisateurDepotDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexUtilisateurDepotModule {}
