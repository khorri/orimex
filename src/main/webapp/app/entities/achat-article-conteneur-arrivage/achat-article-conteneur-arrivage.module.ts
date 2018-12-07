import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OrimexSharedModule } from 'app/shared';
import {
    AchatArticleConteneurArrivageComponent,
    AchatArticleConteneurArrivageDetailComponent,
    AchatArticleConteneurArrivageUpdateComponent,
    AchatArticleConteneurArrivageDeletePopupComponent,
    AchatArticleConteneurArrivageDeleteDialogComponent,
    achatArticleConteneurArrivageRoute,
    achatArticleConteneurArrivagePopupRoute
} from './';

const ENTITY_STATES = [...achatArticleConteneurArrivageRoute, ...achatArticleConteneurArrivagePopupRoute];

@NgModule({
    imports: [OrimexSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        AchatArticleConteneurArrivageComponent,
        AchatArticleConteneurArrivageDetailComponent,
        AchatArticleConteneurArrivageUpdateComponent,
        AchatArticleConteneurArrivageDeleteDialogComponent,
        AchatArticleConteneurArrivageDeletePopupComponent
    ],
    entryComponents: [
        AchatArticleConteneurArrivageComponent,
        AchatArticleConteneurArrivageUpdateComponent,
        AchatArticleConteneurArrivageDeleteDialogComponent,
        AchatArticleConteneurArrivageDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexAchatArticleConteneurArrivageModule {}
