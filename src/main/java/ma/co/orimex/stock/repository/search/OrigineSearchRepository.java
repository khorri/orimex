package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Origine;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Origine entity.
 */
public interface OrigineSearchRepository extends ElasticsearchRepository<Origine, Long> {
}
