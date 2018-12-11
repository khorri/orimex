package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.UtilisateurDepotDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing UtilisateurDepot.
 */
public interface UtilisateurDepotService {

    /**
     * Save a utilisateurDepot.
     *
     * @param utilisateurDepotDTO the entity to save
     * @return the persisted entity
     */
    UtilisateurDepotDTO save(UtilisateurDepotDTO utilisateurDepotDTO);

    /**
     * Get all the utilisateurDepots.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurDepotDTO> findAll(Pageable pageable);


    /**
     * Get the "id" utilisateurDepot.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<UtilisateurDepotDTO> findOne(Long id);

    /**
     * Delete the "id" utilisateurDepot.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the utilisateurDepot corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<UtilisateurDepotDTO> search(String query, Pageable pageable);
}
