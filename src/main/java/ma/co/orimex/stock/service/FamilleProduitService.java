package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.FamilleProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing FamilleProduit.
 */
public interface FamilleProduitService {

    /**
     * Save a familleProduit.
     *
     * @param familleProduitDTO the entity to save
     * @return the persisted entity
     */
    FamilleProduitDTO save(FamilleProduitDTO familleProduitDTO);

    /**
     * Get all the familleProduits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FamilleProduitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" familleProduit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FamilleProduitDTO> findOne(Long id);

    /**
     * Delete the "id" familleProduit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the familleProduit corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FamilleProduitDTO> search(String query, Pageable pageable);
}
