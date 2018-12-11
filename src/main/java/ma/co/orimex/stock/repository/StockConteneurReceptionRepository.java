package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.StockConteneurReception;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StockConteneurReception entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockConteneurReceptionRepository extends JpaRepository<StockConteneurReception, Long> {

}
