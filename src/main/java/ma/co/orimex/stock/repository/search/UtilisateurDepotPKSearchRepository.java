package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.UtilisateurDepotPK;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UtilisateurDepotPK entity.
 */
public interface UtilisateurDepotPKSearchRepository extends ElasticsearchRepository<UtilisateurDepotPK, Long> {
}
