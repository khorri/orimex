import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { OrimexAchatArrivageModule } from './achat-arrivage/achat-arrivage.module';
import { OrimexAchatArticleConteneurArrivageModule } from './achat-article-conteneur-arrivage/achat-article-conteneur-arrivage.module';
import { OrimexAchatBanqueModule } from './achat-banque/achat-banque.module';
import { OrimexAchatDossierModule } from './achat-dossier/achat-dossier.module';
import { OrimexAchatFactureModule } from './achat-facture/achat-facture.module';
import { OrimexAchatConteneurArrivageModule } from './achat-conteneur-arrivage/achat-conteneur-arrivage.module';
import { OrimexAchatStatutDossierModule } from './achat-statut-dossier/achat-statut-dossier.module';
import { OrimexAchatTypePaiementModule } from './achat-type-paiement/achat-type-paiement.module';
import { OrimexAchatDeviseModule } from './achat-devise/achat-devise.module';
import { OrimexAchatProformaModule } from './achat-proforma/achat-proforma.module';
import { OrimexStockArticleConteneurReceptionModule } from './stock-article-conteneur-reception/stock-article-conteneur-reception.module';
import { OrimexAchatLigneProformaModule } from './achat-ligne-proforma/achat-ligne-proforma.module';
import { OrimexProduitModule } from './produit/produit.module';
import { OrimexStockConteneurReceptionModule } from './stock-conteneur-reception/stock-conteneur-reception.module';
import { OrimexCouleurModule } from './couleur/couleur.module';
import { OrimexFamilleProduitModule } from './famille-produit/famille-produit.module';
import { OrimexRecuperationModule } from './recuperation/recuperation.module';
import { OrimexRetourModule } from './retour/retour.module';
import { OrimexUtilisateurModule } from './utilisateur/utilisateur.module';
import { OrimexDepotModule } from './depot/depot.module';
import { OrimexCasseModule } from './casse/casse.module';
import { OrimexReceptionModule } from './reception/reception.module';
import { OrimexSortieModule } from './sortie/sortie.module';
import { OrimexStockReceptionModule } from './stock-reception/stock-reception.module';
import { OrimexTransfertModule } from './transfert/transfert.module';
import { OrimexUtilisateurDepotModule } from './utilisateur-depot/utilisateur-depot.module';
import { OrimexCaisseModule } from './caisse/caisse.module';
import { OrimexBonModule } from './bon/bon.module';
import { OrimexCamionModule } from './camion/camion.module';
import { OrimexConteneurModule } from './conteneur/conteneur.module';
import { OrimexUtilisateurProfilModule } from './utilisateur-profil/utilisateur-profil.module';
import { OrimexAchatArticleLigneProformaModule } from './achat-article-ligne-proforma/achat-article-ligne-proforma.module';
import { OrimexAchatArticleConteneurReceptionModule } from './achat-article-conteneur-reception/achat-article-conteneur-reception.module';
import { OrimexJourFerierModule } from './jour-ferier/jour-ferier.module';
import { OrimexMenuModule } from './menu/menu.module';
import { OrimexActionModule } from './action/action.module';
import { OrimexProfilActionModule } from './profil-action/profil-action.module';
import { OrimexProfilModule } from './profil/profil.module';
import { OrimexAchatConteneurReceptionModule } from './achat-conteneur-reception/achat-conteneur-reception.module';
import { OrimexAchatFournisseurModule } from './achat-fournisseur/achat-fournisseur.module';
import { OrimexAuthorityModule } from './authority/authority.module';
import { OrimexProfilActionPKModule } from './profil-action-pk/profil-action-pk.module';
import { OrimexUtilisateurDepotPKModule } from './utilisateur-depot-pk/utilisateur-depot-pk.module';
import { OrimexUtilisateurProfilPKModule } from './utilisateur-profil-pk/utilisateur-profil-pk.module';
import { OrimexTypeBonModule } from './type-bon/type-bon.module';
import { OrimexVilleModule } from './ville/ville.module';
import { OrimexOrigineModule } from './origine/origine.module';
import { OrimexAchatPrevisionArrivageModule } from './achat-prevision-arrivage/achat-prevision-arrivage.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        OrimexAchatArrivageModule,
        OrimexAchatArticleConteneurArrivageModule,
        OrimexAchatBanqueModule,
        OrimexAchatDossierModule,
        OrimexAchatFactureModule,
        OrimexAchatConteneurArrivageModule,
        OrimexAchatStatutDossierModule,
        OrimexAchatTypePaiementModule,
        OrimexAchatDeviseModule,
        OrimexAchatProformaModule,
        OrimexStockArticleConteneurReceptionModule,
        OrimexAchatLigneProformaModule,
        OrimexProduitModule,
        OrimexStockConteneurReceptionModule,
        OrimexCouleurModule,
        OrimexFamilleProduitModule,
        OrimexRecuperationModule,
        OrimexRetourModule,
        OrimexUtilisateurModule,
        OrimexDepotModule,
        OrimexCasseModule,
        OrimexReceptionModule,
        OrimexSortieModule,
        OrimexStockReceptionModule,
        OrimexTransfertModule,
        OrimexUtilisateurDepotModule,
        OrimexCaisseModule,
        OrimexBonModule,
        OrimexCamionModule,
        OrimexConteneurModule,
        OrimexUtilisateurProfilModule,
        OrimexAchatArticleLigneProformaModule,
        OrimexAchatArticleConteneurReceptionModule,
        OrimexJourFerierModule,
        OrimexMenuModule,
        OrimexActionModule,
        OrimexProfilActionModule,
        OrimexProfilModule,
        OrimexAchatConteneurReceptionModule,
        OrimexAchatFournisseurModule,
        OrimexAuthorityModule,
        OrimexProfilActionPKModule,
        OrimexUtilisateurDepotPKModule,
        OrimexUtilisateurProfilPKModule,
        OrimexTypeBonModule,
        OrimexVilleModule,
        OrimexOrigineModule,
        OrimexAchatPrevisionArrivageModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class OrimexEntityModule {}
