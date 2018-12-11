package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatDossier;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatDossier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatDossierRepository extends JpaRepository<AchatDossier, Long> {

}
