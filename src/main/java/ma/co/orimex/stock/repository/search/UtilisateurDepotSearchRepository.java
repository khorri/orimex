package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.UtilisateurDepot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the UtilisateurDepot entity.
 */
public interface UtilisateurDepotSearchRepository extends ElasticsearchRepository<UtilisateurDepot, Long> {
}
