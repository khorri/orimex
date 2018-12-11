package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.UtilisateurProfil;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UtilisateurProfil entity.
 */
public interface UtilisateurProfilSearchRepository extends ElasticsearchRepository<UtilisateurProfil, Long> {
}
