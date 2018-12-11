package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatConteneurReception;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatConteneurReception entity.
 */
public interface AchatConteneurReceptionSearchRepository extends ElasticsearchRepository<AchatConteneurReception, Long> {
}
