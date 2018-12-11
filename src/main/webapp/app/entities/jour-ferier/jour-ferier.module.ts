import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    JourFerierComponent,
    JourFerierDetailComponent,
    JourFerierUpdateComponent,
    JourFerierDeletePopupComponent,
    JourFerierDeleteDialogComponent,
    jourFerierRoute,
    jourFerierPopupRoute
} from './';

const ENTITY_STATES = [...jourFerierRoute, ...jourFerierPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        JourFerierComponent,
        JourFerierDetailComponent,
        JourFerierUpdateComponent,
        JourFerierDeleteDialogComponent,
        JourFerierDeletePopupComponent
    ],
    entryComponents: [JourFerierComponent, JourFerierUpdateComponent, JourFerierDeleteDialogComponent, JourFerierDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexJourFerierModule {}
