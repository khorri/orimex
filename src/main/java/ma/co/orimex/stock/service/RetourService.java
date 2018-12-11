package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.RetourDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Retour.
 */
public interface RetourService {

    /**
     * Save a retour.
     *
     * @param retourDTO the entity to save
     * @return the persisted entity
     */
    RetourDTO save(RetourDTO retourDTO);

    /**
     * Get all the retours.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RetourDTO> findAll(Pageable pageable);


    /**
     * Get the "id" retour.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<RetourDTO> findOne(Long id);

    /**
     * Delete the "id" retour.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the retour corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RetourDTO> search(String query, Pageable pageable);
}
