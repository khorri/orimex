package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatFournisseur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatFournisseur entity.
 */
public interface AchatFournisseurSearchRepository extends ElasticsearchRepository<AchatFournisseur, Long> {
}
