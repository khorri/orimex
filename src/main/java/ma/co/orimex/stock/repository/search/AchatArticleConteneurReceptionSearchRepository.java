package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatArticleConteneurReception;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatArticleConteneurReception entity.
 */
public interface AchatArticleConteneurReceptionSearchRepository extends ElasticsearchRepository<AchatArticleConteneurReception, Long> {
}
