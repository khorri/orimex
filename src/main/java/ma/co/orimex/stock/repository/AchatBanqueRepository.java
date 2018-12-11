package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatBanque;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatBanque entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatBanqueRepository extends JpaRepository<AchatBanque, Long> {

}
