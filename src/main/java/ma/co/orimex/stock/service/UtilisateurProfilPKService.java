package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.UtilisateurProfilPKDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UtilisateurProfilPK.
 */
public interface UtilisateurProfilPKService {

    /**
     * Save a utilisateurProfilPK.
     *
     * @param utilisateurProfilPKDTO the entity to save
     * @return the persisted entity
     */
    UtilisateurProfilPKDTO save(UtilisateurProfilPKDTO utilisateurProfilPKDTO);

    /**
     * Get all the utilisateurProfilPKS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurProfilPKDTO> findAll(Pageable pageable);


    /**
     * Get the "id" utilisateurProfilPK.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtilisateurProfilPKDTO> findOne(Long id);

    /**
     * Delete the "id" utilisateurProfilPK.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the utilisateurProfilPK corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurProfilPKDTO> search(String query, Pageable pageable);
}
