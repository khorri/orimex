package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatStatutDossierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatStatutDossier.
 */
public interface AchatStatutDossierService {

    /**
     * Save a achatStatutDossier.
     *
     * @param achatStatutDossierDTO the entity to save
     * @return the persisted entity
     */
    AchatStatutDossierDTO save(AchatStatutDossierDTO achatStatutDossierDTO);

    /**
     * Get all the achatStatutDossiers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatStatutDossierDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatStatutDossier.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatStatutDossierDTO> findOne(Long id);

    /**
     * Delete the "id" achatStatutDossier.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatStatutDossier corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatStatutDossierDTO> search(String query, Pageable pageable);
}
