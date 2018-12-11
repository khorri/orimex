package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Couleur;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Couleur entity.
 */
public interface CouleurSearchRepository extends ElasticsearchRepository<Couleur, Long> {
}
