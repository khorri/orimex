package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatStatutDossier;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatStatutDossier entity.
 */
public interface AchatStatutDossierSearchRepository extends ElasticsearchRepository<AchatStatutDossier, Long> {
}
