package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.FamilleProduit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FamilleProduit entity.
 */
public interface FamilleProduitSearchRepository extends ElasticsearchRepository<FamilleProduit, Long> {
}
