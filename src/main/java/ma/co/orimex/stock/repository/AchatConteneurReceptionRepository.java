package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatConteneurReception;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatConteneurReception entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatConteneurReceptionRepository extends JpaRepository<AchatConteneurReception, Long> {

}
