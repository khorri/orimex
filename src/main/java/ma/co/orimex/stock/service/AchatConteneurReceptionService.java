package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatConteneurReceptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatConteneurReception.
 */
public interface AchatConteneurReceptionService {

    /**
     * Save a achatConteneurReception.
     *
     * @param achatConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    AchatConteneurReceptionDTO save(AchatConteneurReceptionDTO achatConteneurReceptionDTO);

    /**
     * Get all the achatConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatConteneurReceptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatConteneurReception.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatConteneurReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" achatConteneurReception.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatConteneurReceptionDTO> search(String query, Pageable pageable);
}
