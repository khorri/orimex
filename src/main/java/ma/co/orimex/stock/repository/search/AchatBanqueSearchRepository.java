package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatBanque;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatBanque entity.
 */
public interface AchatBanqueSearchRepository extends ElasticsearchRepository<AchatBanque, Long> {
}
