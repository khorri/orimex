import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    StockArticleConteneurReceptionComponent,
    StockArticleConteneurReceptionDetailComponent,
    StockArticleConteneurReceptionUpdateComponent,
    StockArticleConteneurReceptionDeletePopupComponent,
    StockArticleConteneurReceptionDeleteDialogComponent,
    stockArticleConteneurReceptionRoute,
    stockArticleConteneurReceptionPopupRoute
} from './';

const ENTITY_STATES = [...stockArticleConteneurReceptionRoute, ...stockArticleConteneurReceptionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StockArticleConteneurReceptionComponent,
        StockArticleConteneurReceptionDetailComponent,
        StockArticleConteneurReceptionUpdateComponent,
        StockArticleConteneurReceptionDeleteDialogComponent,
        StockArticleConteneurReceptionDeletePopupComponent
    ],
    entryComponents: [
        StockArticleConteneurReceptionComponent,
        StockArticleConteneurReceptionUpdateComponent,
        StockArticleConteneurReceptionDeleteDialogComponent,
        StockArticleConteneurReceptionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexStockArticleConteneurReceptionModule {}
