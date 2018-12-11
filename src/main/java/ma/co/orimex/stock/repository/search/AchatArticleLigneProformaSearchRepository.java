package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatArticleLigneProforma;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatArticleLigneProforma entity.
 */
public interface AchatArticleLigneProformaSearchRepository extends ElasticsearchRepository<AchatArticleLigneProforma, Long> {
}
