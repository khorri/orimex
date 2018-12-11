package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.StockArticleConteneurReception;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StockArticleConteneurReception entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockArticleConteneurReceptionRepository extends JpaRepository<StockArticleConteneurReception, Long> {

}
