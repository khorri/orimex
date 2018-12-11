package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.TypeBon;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TypeBon entity.
 */
public interface TypeBonSearchRepository extends ElasticsearchRepository<TypeBon, Long> {
}
