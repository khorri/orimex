package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatArticleConteneurArrivageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatArticleConteneurArrivage.
 */
public interface AchatArticleConteneurArrivageService {

    /**
     * Save a achatArticleConteneurArrivage.
     *
     * @param achatArticleConteneurArrivageDTO the entity to save
     * @return the persisted entity
     */
    AchatArticleConteneurArrivageDTO save(AchatArticleConteneurArrivageDTO achatArticleConteneurArrivageDTO);

    /**
     * Get all the achatArticleConteneurArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArticleConteneurArrivageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatArticleConteneurArrivage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatArticleConteneurArrivageDTO> findOne(Long id);

    /**
     * Delete the "id" achatArticleConteneurArrivage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatArticleConteneurArrivage corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArticleConteneurArrivageDTO> search(String query, Pageable pageable);
}
