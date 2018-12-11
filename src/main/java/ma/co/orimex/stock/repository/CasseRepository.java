package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Casse;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Casse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CasseRepository extends JpaRepository<Casse, Long> {

}
