package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatArticleLigneProforma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatArticleLigneProforma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatArticleLigneProformaRepository extends JpaRepository<AchatArticleLigneProforma, Long> {

}
