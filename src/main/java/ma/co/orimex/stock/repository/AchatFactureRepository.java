package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatFacture;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatFacture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatFactureRepository extends JpaRepository<AchatFacture, Long> {

}
