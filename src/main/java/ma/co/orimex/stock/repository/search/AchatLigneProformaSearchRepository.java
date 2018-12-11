package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatLigneProforma;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatLigneProforma entity.
 */
public interface AchatLigneProformaSearchRepository extends ElasticsearchRepository<AchatLigneProforma, Long> {
}
