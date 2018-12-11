package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.ProfilAction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProfilAction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilActionRepository extends JpaRepository<ProfilAction, Long> {

}
