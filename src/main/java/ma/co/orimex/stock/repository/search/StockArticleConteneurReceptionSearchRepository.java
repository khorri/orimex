package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.StockArticleConteneurReception;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StockArticleConteneurReception entity.
 */
public interface StockArticleConteneurReceptionSearchRepository extends ElasticsearchRepository<StockArticleConteneurReception, Long> {
}
