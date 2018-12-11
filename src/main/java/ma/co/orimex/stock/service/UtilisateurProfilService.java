package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.UtilisateurProfilDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UtilisateurProfil.
 */
public interface UtilisateurProfilService {

    /**
     * Save a utilisateurProfil.
     *
     * @param utilisateurProfilDTO the entity to save
     * @return the persisted entity
     */
    UtilisateurProfilDTO save(UtilisateurProfilDTO utilisateurProfilDTO);

    /**
     * Get all the utilisateurProfils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurProfilDTO> findAll(Pageable pageable);


    /**
     * Get the "id" utilisateurProfil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtilisateurProfilDTO> findOne(Long id);

    /**
     * Delete the "id" utilisateurProfil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the utilisateurProfil corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurProfilDTO> search(String query, Pageable pageable);
}
