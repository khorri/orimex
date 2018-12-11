package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatFacture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatFacture entity.
 */
public interface AchatFactureSearchRepository extends ElasticsearchRepository<AchatFacture, Long> {
}
