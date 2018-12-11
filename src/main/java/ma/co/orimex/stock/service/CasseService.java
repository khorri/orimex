package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.CasseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Casse.
 */
public interface CasseService {

    /**
     * Save a casse.
     *
     * @param casseDTO the entity to save
     * @return the persisted entity
     */
    CasseDTO save(CasseDTO casseDTO);

    /**
     * Get all the casses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CasseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" casse.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CasseDTO> findOne(Long id);

    /**
     * Delete the "id" casse.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the casse corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CasseDTO> search(String query, Pageable pageable);
}
