package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ProfilActionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ProfilAction.
 */
public interface ProfilActionService {

    /**
     * Save a profilAction.
     *
     * @param profilActionDTO the entity to save
     * @return the persisted entity
     */
    ProfilActionDTO save(ProfilActionDTO profilActionDTO);

    /**
     * Get all the profilActions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfilActionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profilAction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProfilActionDTO> findOne(Long id);

    /**
     * Delete the "id" profilAction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the profilAction corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfilActionDTO> search(String query, Pageable pageable);
}
