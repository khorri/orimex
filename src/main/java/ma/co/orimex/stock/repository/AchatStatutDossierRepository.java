package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatStatutDossier;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatStatutDossier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatStatutDossierRepository extends JpaRepository<AchatStatutDossier, Long> {

}
