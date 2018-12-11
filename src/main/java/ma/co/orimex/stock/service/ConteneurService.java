package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ConteneurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Conteneur.
 */
public interface ConteneurService {

    /**
     * Save a conteneur.
     *
     * @param conteneurDTO the entity to save
     * @return the persisted entity
     */
    ConteneurDTO save(ConteneurDTO conteneurDTO);

    /**
     * Get all the conteneurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConteneurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" conteneur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ConteneurDTO> findOne(Long id);

    /**
     * Delete the "id" conteneur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the conteneur corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConteneurDTO> search(String query, Pageable pageable);
}
