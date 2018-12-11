package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.StockReceptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StockReception.
 */
public interface StockReceptionService {

    /**
     * Save a stockReception.
     *
     * @param stockReceptionDTO the entity to save
     * @return the persisted entity
     */
    StockReceptionDTO save(StockReceptionDTO stockReceptionDTO);

    /**
     * Get all the stockReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockReceptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" stockReception.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StockReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" stockReception.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the stockReception corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockReceptionDTO> search(String query, Pageable pageable);
}
