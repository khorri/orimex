package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Sortie;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Sortie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SortieRepository extends JpaRepository<Sortie, Long> {

}
