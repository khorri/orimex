package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.BonDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Bon.
 */
public interface BonService {

    /**
     * Save a bon.
     *
     * @param bonDTO the entity to save
     * @return the persisted entity
     */
    BonDTO save(BonDTO bonDTO);

    /**
     * Get all the bons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" bon.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BonDTO> findOne(Long id);

    /**
     * Delete the "id" bon.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the bon corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<BonDTO> search(String query, Pageable pageable);
}
