import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    StockConteneurReceptionComponent,
    StockConteneurReceptionDetailComponent,
    StockConteneurReceptionUpdateComponent,
    StockConteneurReceptionDeletePopupComponent,
    StockConteneurReceptionDeleteDialogComponent,
    stockConteneurReceptionRoute,
    stockConteneurReceptionPopupRoute
} from './';

const ENTITY_STATES = [...stockConteneurReceptionRoute, ...stockConteneurReceptionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StockConteneurReceptionComponent,
        StockConteneurReceptionDetailComponent,
        StockConteneurReceptionUpdateComponent,
        StockConteneurReceptionDeleteDialogComponent,
        StockConteneurReceptionDeletePopupComponent
    ],
    entryComponents: [
        StockConteneurReceptionComponent,
        StockConteneurReceptionUpdateComponent,
        StockConteneurReceptionDeleteDialogComponent,
        StockConteneurReceptionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexStockConteneurReceptionModule {}
