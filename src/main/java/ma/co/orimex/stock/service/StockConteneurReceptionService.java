package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.StockConteneurReceptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StockConteneurReception.
 */
public interface StockConteneurReceptionService {

    /**
     * Save a stockConteneurReception.
     *
     * @param stockConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    StockConteneurReceptionDTO save(StockConteneurReceptionDTO stockConteneurReceptionDTO);

    /**
     * Get all the stockConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockConteneurReceptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" stockConteneurReception.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StockConteneurReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" stockConteneurReception.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the stockConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockConteneurReceptionDTO> search(String query, Pageable pageable);
}
