package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Couleur;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Couleur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CouleurRepository extends JpaRepository<Couleur, Long> {

}
