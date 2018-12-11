import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatArticleConteneurReceptionComponent,
    AchatArticleConteneurReceptionDetailComponent,
    AchatArticleConteneurReceptionUpdateComponent,
    AchatArticleConteneurReceptionDeletePopupComponent,
    AchatArticleConteneurReceptionDeleteDialogComponent,
    achatArticleConteneurReceptionRoute,
    achatArticleConteneurReceptionPopupRoute
} from './';

const ENTITY_STATES = [...achatArticleConteneurReceptionRoute, ...achatArticleConteneurReceptionPopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatArticleConteneurReceptionComponent,
        AchatArticleConteneurReceptionDetailComponent,
        AchatArticleConteneurReceptionUpdateComponent,
        AchatArticleConteneurReceptionDeleteDialogComponent,
        AchatArticleConteneurReceptionDeletePopupComponent
    ],
    entryComponents: [
        AchatArticleConteneurReceptionComponent,
        AchatArticleConteneurReceptionUpdateComponent,
        AchatArticleConteneurReceptionDeleteDialogComponent,
        AchatArticleConteneurReceptionDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatArticleConteneurReceptionModule {}
