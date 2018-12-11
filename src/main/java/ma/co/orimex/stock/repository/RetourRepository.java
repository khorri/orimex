package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Retour;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Retour entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RetourRepository extends JpaRepository<Retour, Long> {

}
