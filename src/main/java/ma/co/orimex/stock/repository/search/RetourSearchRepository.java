package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Retour;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Retour entity.
 */
public interface RetourSearchRepository extends ElasticsearchRepository<Retour, Long> {
}
