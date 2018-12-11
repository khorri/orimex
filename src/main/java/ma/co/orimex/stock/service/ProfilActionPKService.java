package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ProfilActionPKDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing ProfilActionPK.
 */
public interface ProfilActionPKService {

    /**
     * Save a profilActionPK.
     *
     * @param profilActionPKDTO the entity to save
     * @return the persisted entity
     */
    ProfilActionPKDTO save(ProfilActionPKDTO profilActionPKDTO);

    /**
     * Get all the profilActionPKS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfilActionPKDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profilActionPK.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProfilActionPKDTO> findOne(Long id);

    /**
     * Delete the "id" profilActionPK.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the profilActionPK corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfilActionPKDTO> search(String query, Pageable pageable);
}
