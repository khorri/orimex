package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.JourFerier;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JourFerier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JourFerierRepository extends JpaRepository<JourFerier, Long> {

}
