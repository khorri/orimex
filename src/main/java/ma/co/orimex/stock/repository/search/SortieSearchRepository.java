package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Sortie;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Sortie entity.
 */
public interface SortieSearchRepository extends ElasticsearchRepository<Sortie, Long> {
}
