package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Profil;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Profil entity.
 */
public interface ProfilSearchRepository extends ElasticsearchRepository<Profil, Long> {
}
