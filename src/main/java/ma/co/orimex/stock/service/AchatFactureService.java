package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatFactureDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatFacture.
 */
public interface AchatFactureService {

    /**
     * Save a achatFacture.
     *
     * @param achatFactureDTO the entity to save
     * @return the persisted entity
     */
    AchatFactureDTO save(AchatFactureDTO achatFactureDTO);

    /**
     * Get all the achatFactures.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatFactureDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatFacture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatFactureDTO> findOne(Long id);

    /**
     * Delete the "id" achatFacture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatFacture corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatFactureDTO> search(String query, Pageable pageable);
}
