package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.SortieDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Sortie.
 */
public interface SortieService {

    /**
     * Save a sortie.
     *
     * @param sortieDTO the entity to save
     * @return the persisted entity
     */
    SortieDTO save(SortieDTO sortieDTO);

    /**
     * Get all the sorties.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SortieDTO> findAll(Pageable pageable);


    /**
     * Get the "id" sortie.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SortieDTO> findOne(Long id);

    /**
     * Delete the "id" sortie.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the sortie corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<SortieDTO> search(String query, Pageable pageable);
}
