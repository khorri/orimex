package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.UtilisateurProfil;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the UtilisateurProfil entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UtilisateurProfilRepository extends JpaRepository<UtilisateurProfil, Long> {

}
