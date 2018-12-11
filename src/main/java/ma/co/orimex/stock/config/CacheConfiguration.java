package ma.co.orimex.stock.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(ma.co.orimex.stock.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatArrivage.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatArticleConteneurArrivage.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatArrivage.class.getName() + ".achatFactures", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatBanque.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatBanque.class.getName() + ".achatDossiers", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatDossier.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatDossier.class.getName() + ".achatPrevisionArrivages", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatFacture.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatFacture.class.getName() + ".achatConteneurArrivages", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatConteneurArrivage.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatConteneurArrivage.class.getName() + ".achatArticleConteneurArrivages", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatStatutDossier.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatStatutDossier.class.getName() + ".achatDossiers", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatTypePaiement.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatDevise.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatDevise.class.getName() + ".achatDossiers", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatProforma.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatProforma.class.getName() + ".achatLigneProformas", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.StockArticleConteneurReception.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatLigneProforma.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Produit.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Produit.class.getName() + ".recuperations", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Produit.class.getName() + ".retours", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Produit.class.getName() + ".achatArticleLigneProformas", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Produit.class.getName() + ".achatArticleConteneurArrivages", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.StockConteneurReception.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.StockConteneurReception.class.getName() + ".stockArticleConteneurReceptions", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Couleur.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.FamilleProduit.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Recuperation.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Retour.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".casses", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".receptions", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".sorties", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".retours", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".recuperations", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".utilisateurProfils", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".utilisateurDepots", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".transferts", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Utilisateur.class.getName() + ".stockReceptions", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Depot.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Casse.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Reception.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Sortie.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.StockReception.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Transfert.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.UtilisateurDepot.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Caisse.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Bon.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Bon.class.getName() + ".retours", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Camion.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Conteneur.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.UtilisateurProfil.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatArticleLigneProforma.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatArticleConteneurReception.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.JourFerier.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Menu.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Menu.class.getName() + ".actions", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Menu.class.getName() + ".menus", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Action.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Action.class.getName() + ".profilActions", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.ProfilAction.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Profil.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Profil.class.getName() + ".utilisateurProfils", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Profil.class.getName() + ".profilActions", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatConteneurReception.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatFournisseur.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.ProfilActionPK.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.UtilisateurDepotPK.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.UtilisateurProfilPK.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.TypeBon.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Ville.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Ville.class.getName() + ".depots", jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.Origine.class.getName(), jcacheConfiguration);
            cm.createCache(ma.co.orimex.stock.domain.AchatPrevisionArrivage.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
