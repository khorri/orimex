package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.StockReception;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StockReception entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StockReceptionRepository extends JpaRepository<StockReception, Long> {

}
