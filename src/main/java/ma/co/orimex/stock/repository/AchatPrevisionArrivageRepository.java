package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatPrevisionArrivage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatPrevisionArrivage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatPrevisionArrivageRepository extends JpaRepository<AchatPrevisionArrivage, Long> {

}
