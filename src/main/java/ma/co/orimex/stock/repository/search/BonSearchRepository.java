package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Bon;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Bon entity.
 */
public interface BonSearchRepository extends ElasticsearchRepository<Bon, Long> {
}
