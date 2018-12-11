package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.JourFerierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing JourFerier.
 */
public interface JourFerierService {

    /**
     * Save a jourFerier.
     *
     * @param jourFerierDTO the entity to save
     * @return the persisted entity
     */
    JourFerierDTO save(JourFerierDTO jourFerierDTO);

    /**
     * Get all the jourFeriers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JourFerierDTO> findAll(Pageable pageable);


    /**
     * Get the "id" jourFerier.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<JourFerierDTO> findOne(Long id);

    /**
     * Delete the "id" jourFerier.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the jourFerier corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<JourFerierDTO> search(String query, Pageable pageable);
}
