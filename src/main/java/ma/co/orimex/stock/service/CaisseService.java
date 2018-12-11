package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.CaisseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Caisse.
 */
public interface CaisseService {

    /**
     * Save a caisse.
     *
     * @param caisseDTO the entity to save
     * @return the persisted entity
     */
    CaisseDTO save(CaisseDTO caisseDTO);

    /**
     * Get all the caisses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaisseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" caisse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CaisseDTO> findOne(Long id);

    /**
     * Delete the "id" caisse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the caisse corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CaisseDTO> search(String query, Pageable pageable);
}
