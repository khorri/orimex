package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.ProfilAction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ProfilAction entity.
 */
public interface ProfilActionSearchRepository extends ElasticsearchRepository<ProfilAction, Long> {
}
