package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatDossier;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatDossier entity.
 */
public interface AchatDossierSearchRepository extends ElasticsearchRepository<AchatDossier, Long> {
}
