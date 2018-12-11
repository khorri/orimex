package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatPrevisionArrivage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatPrevisionArrivage entity.
 */
public interface AchatPrevisionArrivageSearchRepository extends ElasticsearchRepository<AchatPrevisionArrivage, Long> {
}
