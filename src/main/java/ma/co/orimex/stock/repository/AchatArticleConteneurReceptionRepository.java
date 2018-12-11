package ma.co.orimex.stock.repository;

import ma.co.orimex.stock.domain.AchatArticleConteneurReception;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AchatArticleConteneurReception entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchatArticleConteneurReceptionRepository extends JpaRepository<AchatArticleConteneurReception, Long> {

}
