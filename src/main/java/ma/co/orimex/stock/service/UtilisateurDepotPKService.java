package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.UtilisateurDepotPKDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UtilisateurDepotPK.
 */
public interface UtilisateurDepotPKService {

    /**
     * Save a utilisateurDepotPK.
     *
     * @param utilisateurDepotPKDTO the entity to save
     * @return the persisted entity
     */
    UtilisateurDepotPKDTO save(UtilisateurDepotPKDTO utilisateurDepotPKDTO);

    /**
     * Get all the utilisateurDepotPKS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurDepotPKDTO> findAll(Pageable pageable);


    /**
     * Get the "id" utilisateurDepotPK.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtilisateurDepotPKDTO> findOne(Long id);

    /**
     * Delete the "id" utilisateurDepotPK.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the utilisateurDepotPK corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurDepotPKDTO> search(String query, Pageable pageable);
}
