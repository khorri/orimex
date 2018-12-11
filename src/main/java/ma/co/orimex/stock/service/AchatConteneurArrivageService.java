package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatConteneurArrivageDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatConteneurArrivage.
 */
public interface AchatConteneurArrivageService {

    /**
     * Save a achatConteneurArrivage.
     *
     * @param achatConteneurArrivageDTO the entity to save
     * @return the persisted entity
     */
    AchatConteneurArrivageDTO save(AchatConteneurArrivageDTO achatConteneurArrivageDTO);

    /**
     * Get all the achatConteneurArrivages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatConteneurArrivageDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatConteneurArrivage.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatConteneurArrivageDTO> findOne(Long id);

    /**
     * Delete the "id" achatConteneurArrivage.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatConteneurArrivage corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatConteneurArrivageDTO> search(String query, Pageable pageable);
}
