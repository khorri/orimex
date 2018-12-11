package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Camion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Camion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CamionRepository extends JpaRepository<Camion, Long> {

}
