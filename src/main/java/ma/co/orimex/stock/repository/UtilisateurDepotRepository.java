package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.UtilisateurDepot;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UtilisateurDepot entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtilisateurDepotRepository extends JpaRepository<UtilisateurDepot, Long> {

}
