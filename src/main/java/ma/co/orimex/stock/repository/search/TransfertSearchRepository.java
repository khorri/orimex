package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Transfert;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Transfert entity.
 */
public interface TransfertSearchRepository extends ElasticsearchRepository<Transfert, Long> {
}
