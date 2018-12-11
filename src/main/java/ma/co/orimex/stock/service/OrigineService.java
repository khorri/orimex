package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.OrigineDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Origine.
 */
public interface OrigineService {

    /**
     * Save a origine.
     *
     * @param origineDTO the entity to save
     * @return the persisted entity
     */
    OrigineDTO save(OrigineDTO origineDTO);

    /**
     * Get all the origines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrigineDTO> findAll(Pageable pageable);


    /**
     * Get the "id" origine.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<OrigineDTO> findOne(Long id);

    /**
     * Delete the "id" origine.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the origine corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<OrigineDTO> search(String query, Pageable pageable);
}
