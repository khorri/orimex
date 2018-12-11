package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Conteneur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Conteneur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConteneurRepository extends JpaRepository<Conteneur, Long> {

}
