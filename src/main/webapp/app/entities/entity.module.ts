import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { OrimexAchatArrivageModule } from './achat-arrivage/achat-arrivage.module';
import { OrimexAchatArticleConteneurArrivageModule } from './achat-article-conteneur-arrivage/achat-article-conteneur-arrivage.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        OrimexAchatArrivageModule,
        OrimexAchatArticleConteneurArrivageModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexEntityModule {}
