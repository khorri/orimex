package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.TransfertDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Transfert.
 */
public interface TransfertService {

    /**
     * Save a transfert.
     *
     * @param transfertDTO the entity to save
     * @return the persisted entity
     */
    TransfertDTO save(TransfertDTO transfertDTO);

    /**
     * Get all the transferts.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TransfertDTO> findAll(Pageable pageable);


    /**
     * Get the "id" transfert.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransfertDTO> findOne(Long id);

    /**
     * Delete the "id" transfert.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the transfert corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TransfertDTO> search(String query, Pageable pageable);
}
