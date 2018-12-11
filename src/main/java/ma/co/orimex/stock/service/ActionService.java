package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.ActionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Action.
 */
public interface ActionService {

    /**
     * Save a action.
     *
     * @param actionDTO the entity to save
     * @return the persisted entity
     */
    ActionDTO save(ActionDTO actionDTO);

    /**
     * Get all the actions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" action.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ActionDTO> findOne(Long id);

    /**
     * Delete the "id" action.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the action corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ActionDTO> search(String query, Pageable pageable);
}
