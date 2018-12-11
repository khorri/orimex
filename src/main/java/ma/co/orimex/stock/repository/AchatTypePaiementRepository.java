package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatTypePaiement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatTypePaiement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatTypePaiementRepository extends JpaRepository<AchatTypePaiement, Long> {

}
