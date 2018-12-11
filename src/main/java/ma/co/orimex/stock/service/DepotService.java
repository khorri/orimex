package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.DepotDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Depot.
 */
public interface DepotService {

    /**
     * Save a depot.
     *
     * @param depotDTO the entity to save
     * @return the persisted entity
     */
    DepotDTO save(DepotDTO depotDTO);

    /**
     * Get all the depots.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DepotDTO> findAll(Pageable pageable);


    /**
     * Get the "id" depot.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DepotDTO> findOne(Long id);

    /**
     * Delete the "id" depot.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the depot corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DepotDTO> search(String query, Pageable pageable);
}
