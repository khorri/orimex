package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatProforma;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatProforma entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatProformaRepository extends JpaRepository<AchatProforma, Long> {

}
