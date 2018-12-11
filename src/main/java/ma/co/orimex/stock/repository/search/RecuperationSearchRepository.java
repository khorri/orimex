package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Recuperation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Recuperation entity.
 */
public interface RecuperationSearchRepository extends ElasticsearchRepository<Recuperation, Long> {
}
