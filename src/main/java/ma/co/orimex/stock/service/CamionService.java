package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.CamionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Camion.
 */
public interface CamionService {

    /**
     * Save a camion.
     *
     * @param camionDTO the entity to save
     * @return the persisted entity
     */
    CamionDTO save(CamionDTO camionDTO);

    /**
     * Get all the camions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CamionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" camion.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CamionDTO> findOne(Long id);

    /**
     * Delete the "id" camion.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the camion corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CamionDTO> search(String query, Pageable pageable);
}
