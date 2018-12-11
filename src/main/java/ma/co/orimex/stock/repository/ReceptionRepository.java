package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Reception;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reception entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceptionRepository extends JpaRepository<Reception, Long> {

}
