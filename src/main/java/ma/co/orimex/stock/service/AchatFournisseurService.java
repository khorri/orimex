package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.AchatFournisseurDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing AchatFournisseur.
 */
public interface AchatFournisseurService {

    /**
     * Save a achatFournisseur.
     *
     * @param achatFournisseurDTO the entity to save
     * @return the persisted entity
     */
    AchatFournisseurDTO save(AchatFournisseurDTO achatFournisseurDTO);

    /**
     * Get all the achatFournisseurs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatFournisseurDTO> findAll(Pageable pageable);


    /**
     * Get the "id" achatFournisseur.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AchatFournisseurDTO> findOne(Long id);

    /**
     * Delete the "id" achatFournisseur.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the achatFournisseur corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<AchatFournisseurDTO> search(String query, Pageable pageable);
}
