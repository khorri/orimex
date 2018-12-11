package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.TypeBon;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeBon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeBonRepository extends JpaRepository<TypeBon, Long> {

}
