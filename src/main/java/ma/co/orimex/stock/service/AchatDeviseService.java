package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatDeviseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatDevise.
 */
public interface AchatDeviseService {

    /**
     * Save a achatDevise.
     *
     * @param achatDeviseDTO the entity to save
     * @return the persisted entity
     */
    AchatDeviseDTO save(AchatDeviseDTO achatDeviseDTO);

    /**
     * Get all the achatDevises.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatDeviseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatDevise.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatDeviseDTO> findOne(Long id);

    /**
     * Delete the "id" achatDevise.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatDevise corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatDeviseDTO> search(String query, Pageable pageable);
}
