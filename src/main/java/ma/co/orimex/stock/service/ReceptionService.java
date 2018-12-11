package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ReceptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Reception.
 */
public interface ReceptionService {

    /**
     * Save a reception.
     *
     * @param receptionDTO the entity to save
     * @return the persisted entity
     */
    ReceptionDTO save(ReceptionDTO receptionDTO);

    /**
     * Get all the receptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReceptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" reception.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" reception.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the reception corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ReceptionDTO> search(String query, Pageable pageable);
}
