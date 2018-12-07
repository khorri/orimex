package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatArticleConteneurArrivage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatArticleConteneurArrivage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatArticleConteneurArrivageRepository extends JpaRepository<AchatArticleConteneurArrivage, Long> {

}
