import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    SortieComponent,
    SortieDetailComponent,
    SortieUpdateComponent,
    SortieDeletePopupComponent,
    SortieDeleteDialogComponent,
    sortieRoute,
    sortiePopupRoute
} from './';

const ENTITY_STATES = [...sortieRoute, ...sortiePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [SortieComponent, SortieDetailComponent, SortieUpdateComponent, SortieDeleteDialogComponent, SortieDeletePopupComponent],
    entryComponents: [SortieComponent, SortieUpdateComponent, SortieDeleteDialogComponent, SortieDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexSortieModule {}
