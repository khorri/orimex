package ma.co.orimex.stock.service;

import ma.co.orimex.stock.service.dto.StockArticleConteneurReceptionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing StockArticleConteneurReception.
 */
public interface StockArticleConteneurReceptionService {

    /**
     * Save a stockArticleConteneurReception.
     *
     * @param stockArticleConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    StockArticleConteneurReceptionDTO save(StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO);

    /**
     * Get all the stockArticleConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockArticleConteneurReceptionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" stockArticleConteneurReception.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StockArticleConteneurReceptionDTO> findOne(Long id);

    /**
     * Delete the "id" stockArticleConteneurReception.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the stockArticleConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<StockArticleConteneurReceptionDTO> search(String query, Pageable pageable);
}
