package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Bon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Bon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BonRepository extends JpaRepository<Bon, Long> {

}
