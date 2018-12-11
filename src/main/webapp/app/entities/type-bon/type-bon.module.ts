import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    TypeBonComponent,
    TypeBonDetailComponent,
    TypeBonUpdateComponent,
    TypeBonDeletePopupComponent,
    TypeBonDeleteDialogComponent,
    typeBonRoute,
    typeBonPopupRoute
} from './';

const ENTITY_STATES = [...typeBonRoute, ...typeBonPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        TypeBonComponent,
        TypeBonDetailComponent,
        TypeBonUpdateComponent,
        TypeBonDeleteDialogComponent,
        TypeBonDeletePopupComponent
    ],
    entryComponents: [TypeBonComponent, TypeBonUpdateComponent, TypeBonDeleteDialogComponent, TypeBonDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexTypeBonModule {}
