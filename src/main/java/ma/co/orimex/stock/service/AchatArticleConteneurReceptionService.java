package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatArticleConteneurReceptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatArticleConteneurReception.
 */
public interface AchatArticleConteneurReceptionService {

    /**
     * Save a achatArticleConteneurReception.
     *
     * @param achatArticleConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    AchatArticleConteneurReceptionDTO save(AchatArticleConteneurReceptionDTO achatArticleConteneurReceptionDTO);

    /**
     * Get all the achatArticleConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArticleConteneurReceptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatArticleConteneurReception.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatArticleConteneurReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" achatArticleConteneurReception.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatArticleConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArticleConteneurReceptionDTO> search(String query, Pageable pageable);
}
