package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatDevise;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatDevise entity.
 */
public interface AchatDeviseSearchRepository extends ElasticsearchRepository<AchatDevise, Long> {
}
