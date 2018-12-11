package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Depot;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Depot entity.
 */
public interface DepotSearchRepository extends ElasticsearchRepository<Depot, Long> {
}
