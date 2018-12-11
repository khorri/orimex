package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.UtilisateurProfilPK;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UtilisateurProfilPK entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtilisateurProfilPKRepository extends JpaRepository<UtilisateurProfilPK, Long> {

}
