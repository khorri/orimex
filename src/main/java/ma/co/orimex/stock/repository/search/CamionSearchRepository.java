package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Camion;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Camion entity.
 */
public interface CamionSearchRepository extends ElasticsearchRepository<Camion, Long> {
}
