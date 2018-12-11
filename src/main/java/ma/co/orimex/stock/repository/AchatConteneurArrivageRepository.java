package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatConteneurArrivage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatConteneurArrivage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatConteneurArrivageRepository extends JpaRepository<AchatConteneurArrivage, Long> {

}
