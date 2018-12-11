package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatPrevisionArrivageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatPrevisionArrivage.
 */
public interface AchatPrevisionArrivageService {

    /**
     * Save a achatPrevisionArrivage.
     *
     * @param achatPrevisionArrivageDTO the entity to save
     * @return the persisted entity
     */
    AchatPrevisionArrivageDTO save(AchatPrevisionArrivageDTO achatPrevisionArrivageDTO);

    /**
     * Get all the achatPrevisionArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatPrevisionArrivageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatPrevisionArrivage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatPrevisionArrivageDTO> findOne(Long id);

    /**
     * Delete the "id" achatPrevisionArrivage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatPrevisionArrivage corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatPrevisionArrivageDTO> search(String query, Pageable pageable);
}
