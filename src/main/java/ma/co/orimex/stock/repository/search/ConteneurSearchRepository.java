package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Conteneur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Conteneur entity.
 */
public interface ConteneurSearchRepository extends ElasticsearchRepository<Conteneur, Long> {
}
