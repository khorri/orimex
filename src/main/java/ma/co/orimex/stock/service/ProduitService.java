package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ProduitDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Produit.
 */
public interface ProduitService {

    /**
     * Save a produit.
     *
     * @param produitDTO the entity to save
     * @return the persisted entity
     */
    ProduitDTO save(ProduitDTO produitDTO);

    /**
     * Get all the produits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProduitDTO> findAll(Pageable pageable);


    /**
     * Get the "id" produit.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ProduitDTO> findOne(Long id);

    /**
     * Delete the "id" produit.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the produit corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProduitDTO> search(String query, Pageable pageable);
}
