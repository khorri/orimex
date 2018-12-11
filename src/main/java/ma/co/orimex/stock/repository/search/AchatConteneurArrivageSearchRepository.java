package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatConteneurArrivage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatConteneurArrivage entity.
 */
public interface AchatConteneurArrivageSearchRepository extends ElasticsearchRepository<AchatConteneurArrivage, Long> {
}
