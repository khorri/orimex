package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.UtilisateurProfilPK;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UtilisateurProfilPK entity.
 */
public interface UtilisateurProfilPKSearchRepository extends ElasticsearchRepository<UtilisateurProfilPK, Long> {
}
