package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Caisse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Caisse entity.
 */
public interface CaisseSearchRepository extends ElasticsearchRepository<Caisse, Long> {
}
