package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatDossierDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatDossier.
 */
public interface AchatDossierService {

    /**
     * Save a achatDossier.
     *
     * @param achatDossierDTO the entity to save
     * @return the persisted entity
     */
    AchatDossierDTO save(AchatDossierDTO achatDossierDTO);

    /**
     * Get all the achatDossiers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatDossierDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatDossier.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatDossierDTO> findOne(Long id);

    /**
     * Delete the "id" achatDossier.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatDossier corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatDossierDTO> search(String query, Pageable pageable);
}
