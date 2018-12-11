package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ProfilDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Profil.
 */
public interface ProfilService {

    /**
     * Save a profil.
     *
     * @param profilDTO the entity to save
     * @return the persisted entity
     */
    ProfilDTO save(ProfilDTO profilDTO);

    /**
     * Get all the profils.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfilDTO> findAll(Pageable pageable);


    /**
     * Get the "id" profil.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProfilDTO> findOne(Long id);

    /**
     * Delete the "id" profil.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the profil corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProfilDTO> search(String query, Pageable pageable);
}
