package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Reception;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Reception entity.
 */
public interface ReceptionSearchRepository extends ElasticsearchRepository<Reception, Long> {
}
