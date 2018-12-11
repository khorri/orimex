import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    ProfilComponent,
    ProfilDetailComponent,
    ProfilUpdateComponent,
    ProfilDeletePopupComponent,
    ProfilDeleteDialogComponent,
    profilRoute,
    profilPopupRoute
} from './';

const ENTITY_STATES = [...profilRoute, ...profilPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ProfilComponent, ProfilDetailComponent, ProfilUpdateComponent, ProfilDeleteDialogComponent, ProfilDeletePopupComponent],
    entryComponents: [ProfilComponent, ProfilUpdateComponent, ProfilDeleteDialogComponent, ProfilDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexProfilModule {}
