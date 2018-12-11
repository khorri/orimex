package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatArticleLigneProformaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatArticleLigneProforma.
 */
public interface AchatArticleLigneProformaService {

    /**
     * Save a achatArticleLigneProforma.
     *
     * @param achatArticleLigneProformaDTO the entity to save
     * @return the persisted entity
     */
    AchatArticleLigneProformaDTO save(AchatArticleLigneProformaDTO achatArticleLigneProformaDTO);

    /**
     * Get all the achatArticleLigneProformas.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArticleLigneProformaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatArticleLigneProforma.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatArticleLigneProformaDTO> findOne(Long id);

    /**
     * Delete the "id" achatArticleLigneProforma.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatArticleLigneProforma corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArticleLigneProformaDTO> search(String query, Pageable pageable);
}
