package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatArrivage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatArrivage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatArrivageRepository extends JpaRepository<AchatArrivage, Long> {

}
