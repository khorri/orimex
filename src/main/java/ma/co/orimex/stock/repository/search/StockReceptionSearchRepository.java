package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.StockReception;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StockReception entity.
 */
public interface StockReceptionSearchRepository extends ElasticsearchRepository<StockReception, Long> {
}
