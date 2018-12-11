package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatProforma;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatProforma entity.
 */
public interface AchatProformaSearchRepository extends ElasticsearchRepository<AchatProforma, Long> {
}
