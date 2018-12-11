package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.ProfilActionPK;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ProfilActionPK entity.
 */
public interface ProfilActionPKSearchRepository extends ElasticsearchRepository<ProfilActionPK, Long> {
}
