package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Authority;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Authority entity.
 */
public interface AuthoritySearchRepository extends ElasticsearchRepository<Authority, Long> {
}
