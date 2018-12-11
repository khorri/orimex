package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.JourFerier;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the JourFerier entity.
 */
public interface JourFerierSearchRepository extends ElasticsearchRepository<JourFerier, Long> {
}
