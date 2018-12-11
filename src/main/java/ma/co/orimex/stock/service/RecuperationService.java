package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.RecuperationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Recuperation.
 */
public interface RecuperationService {

    /**
     * Save a recuperation.
     *
     * @param recuperationDTO the entity to save
     * @return the persisted entity
     */
    RecuperationDTO save(RecuperationDTO recuperationDTO);

    /**
     * Get all the recuperations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RecuperationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" recuperation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RecuperationDTO> findOne(Long id);

    /**
     * Delete the "id" recuperation.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the recuperation corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RecuperationDTO> search(String query, Pageable pageable);
}
