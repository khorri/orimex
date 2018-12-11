package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Casse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Casse entity.
 */
public interface CasseSearchRepository extends ElasticsearchRepository<Casse, Long> {
}
