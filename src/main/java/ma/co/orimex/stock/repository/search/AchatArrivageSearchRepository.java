package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatArrivage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatArrivage entity.
 */
public interface AchatArrivageSearchRepository extends ElasticsearchRepository<AchatArrivage, Long> {
}
