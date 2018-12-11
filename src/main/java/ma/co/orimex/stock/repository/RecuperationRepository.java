package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.Recuperation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Recuperation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecuperationRepository extends JpaRepository<Recuperation, Long> {

}
