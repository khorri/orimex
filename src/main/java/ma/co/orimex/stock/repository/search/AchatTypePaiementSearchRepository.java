package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatTypePaiement;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatTypePaiement entity.
 */
public interface AchatTypePaiementSearchRepository extends ElasticsearchRepository<AchatTypePaiement, Long> {
}
