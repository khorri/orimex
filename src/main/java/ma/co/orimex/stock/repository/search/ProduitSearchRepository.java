package ma.co.orimex.stock.repository.search;

import ma.co.orimex.stock.domain.Produit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Produit entity.
 */
public interface ProduitSearchRepository extends ElasticsearchRepository<Produit, Long> {
}
