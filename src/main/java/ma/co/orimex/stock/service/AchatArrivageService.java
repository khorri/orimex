package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatArrivageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatArrivage.
 */
public interface AchatArrivageService {

    /**
     * Save a achatArrivage.
     *
     * @param achatArrivageDTO the entity to save
     * @return the persisted entity
     */
    AchatArrivageDTO save(AchatArrivageDTO achatArrivageDTO);

    /**
     * Get all the achatArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArrivageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatArrivage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatArrivageDTO> findOne(Long id);

    /**
     * Delete the "id" achatArrivage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatArrivage corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatArrivageDTO> search(String query, Pageable pageable);
}
