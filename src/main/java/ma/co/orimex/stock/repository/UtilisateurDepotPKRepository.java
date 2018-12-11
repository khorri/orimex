package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.UtilisateurDepotPK;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UtilisateurDepotPK entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtilisateurDepotPKRepository extends JpaRepository<UtilisateurDepotPK, Long> {

}
