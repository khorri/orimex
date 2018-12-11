package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatTypePaiementDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatTypePaiement.
 */
public interface AchatTypePaiementService {

    /**
     * Save a achatTypePaiement.
     *
     * @param achatTypePaiementDTO the entity to save
     * @return the persisted entity
     */
    AchatTypePaiementDTO save(AchatTypePaiementDTO achatTypePaiementDTO);

    /**
     * Get all the achatTypePaiements.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatTypePaiementDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatTypePaiement.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatTypePaiementDTO> findOne(Long id);

    /**
     * Delete the "id" achatTypePaiement.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatTypePaiement corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatTypePaiementDTO> search(String query, Pageable pageable);
}
