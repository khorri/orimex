package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.AchatArticleConteneurArrivage;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AchatArticleConteneurArrivage entity.
 */
public interface AchatArticleConteneurArrivageSearchRepository extends ElasticsearchRepository<AchatArticleConteneurArrivage, Long> {
}
