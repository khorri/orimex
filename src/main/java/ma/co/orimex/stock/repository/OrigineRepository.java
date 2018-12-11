package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Origine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Origine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrigineRepository extends JpaRepository<Origine, Long> {

}
