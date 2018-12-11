package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatLigneProforma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatLigneProforma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatLigneProformaRepository extends JpaRepository<AchatLigneProforma, Long> {

}
