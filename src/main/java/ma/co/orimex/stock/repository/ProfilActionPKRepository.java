package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.ProfilActionPK;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProfilActionPK entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProfilActionPKRepository extends JpaRepository<ProfilActionPK, Long> {

}
