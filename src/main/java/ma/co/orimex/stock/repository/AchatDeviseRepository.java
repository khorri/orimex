package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatDevise;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatDevise entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatDeviseRepository extends JpaRepository<AchatDevise, Long> {

}
