package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.StockArticleConteneurReceptionService;
import ma.co.orimex.stock.domain.StockArticleConteneurReception;
import ma.co.orimex.stock.repository.StockArticleConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.StockArticleConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.dto.StockArticleConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.StockArticleConteneurReceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StockArticleConteneurReception.
 */
@Service
@Transactional
public class StockArticleConteneurReceptionServiceImpl implements StockArticleConteneurReceptionService {

    private final Logger log = LoggerFactory.getLogger(StockArticleConteneurReceptionServiceImpl.class);

    private final StockArticleConteneurReceptionRepository stockArticleConteneurReceptionRepository;

    private final StockArticleConteneurReceptionMapper stockArticleConteneurReceptionMapper;

    private final StockArticleConteneurReceptionSearchRepository stockArticleConteneurReceptionSearchRepository;

    public StockArticleConteneurReceptionServiceImpl(StockArticleConteneurReceptionRepository stockArticleConteneurReceptionRepository, StockArticleConteneurReceptionMapper stockArticleConteneurReceptionMapper, StockArticleConteneurReceptionSearchRepository stockArticleConteneurReceptionSearchRepository) {
        this.stockArticleConteneurReceptionRepository = stockArticleConteneurReceptionRepository;
        this.stockArticleConteneurReceptionMapper = stockArticleConteneurReceptionMapper;
        this.stockArticleConteneurReceptionSearchRepository = stockArticleConteneurReceptionSearchRepository;
    }

    /**
     * Save a stockArticleConteneurReception.
     *
     * @param stockArticleConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockArticleConteneurReceptionDTO save(StockArticleConteneurReceptionDTO stockArticleConteneurReceptionDTO) {
        log.debug("Request to save StockArticleConteneurReception : {}", stockArticleConteneurReceptionDTO);

        StockArticleConteneurReception stockArticleConteneurReception = stockArticleConteneurReceptionMapper.toEntity(stockArticleConteneurReceptionDTO);
        stockArticleConteneurReception = stockArticleConteneurReceptionRepository.save(stockArticleConteneurReception);
        StockArticleConteneurReceptionDTO result = stockArticleConteneurReceptionMapper.toDto(stockArticleConteneurReception);
        stockArticleConteneurReceptionSearchRepository.save(stockArticleConteneurReception);
        return result;
    }

    /**
     * Get all the stockArticleConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockArticleConteneurReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockArticleConteneurReceptions");
        return stockArticleConteneurReceptionRepository.findAll(pageable)
            .map(stockArticleConteneurReceptionMapper::toDto);
    }


    /**
     * Get one stockArticleConteneurReception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockArticleConteneurReceptionDTO> findOne(Long id) {
        log.debug("Request to get StockArticleConteneurReception : {}", id);
        return stockArticleConteneurReceptionRepository.findById(id)
            .map(stockArticleConteneurReceptionMapper::toDto);
    }

    /**
     * Delete the stockArticleConteneurReception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockArticleConteneurReception : {}", id);
        stockArticleConteneurReceptionRepository.deleteById(id);
        stockArticleConteneurReceptionSearchRepository.deleteById(id);
    }

    /**
     * Search for the stockArticleConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockArticleConteneurReceptionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of StockArticleConteneurReceptions for query {}", query);
        return stockArticleConteneurReceptionSearchRepository.search(queryStringQuery(query), pageable)
            .map(stockArticleConteneurReceptionMapper::toDto);
    }
}
