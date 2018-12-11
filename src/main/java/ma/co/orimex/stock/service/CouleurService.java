package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.CouleurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Couleur.
 */
public interface CouleurService {

    /**
     * Save a couleur.
     *
     * @param couleurDTO the entity to save
     * @return the persisted entity
     */
    CouleurDTO save(CouleurDTO couleurDTO);

    /**
     * Get all the couleurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CouleurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" couleur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CouleurDTO> findOne(Long id);

    /**
     * Delete the "id" couleur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the couleur corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CouleurDTO> search(String query, Pageable pageable);
}
