package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.StockConteneurReception;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StockConteneurReception entity.
 */
public interface StockConteneurReceptionSearchRepository extends ElasticsearchRepository<StockConteneurReception, Long> {
}
