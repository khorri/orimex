import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    StockReceptionComponent,
    StockReceptionDetailComponent,
    StockReceptionUpdateComponent,
    StockReceptionDeletePopupComponent,
    StockReceptionDeleteDialogComponent,
    stockReceptionRoute,
    stockReceptionPopupRoute
} from './';

const ENTITY_STATES = [...stockReceptionRoute, ...stockReceptionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StockReceptionComponent,
        StockReceptionDetailComponent,
        StockReceptionUpdateComponent,
        StockReceptionDeleteDialogComponent,
        StockReceptionDeletePopupComponent
    ],
    entryComponents: [
        StockReceptionComponent,
        StockReceptionUpdateComponent,
        StockReceptionDeleteDialogComponent,
        StockReceptionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexStockReceptionModule {}
