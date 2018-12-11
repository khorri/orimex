package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.StockReceptionService;
import ma.co.orimex.stock.domain.StockReception;
import ma.co.orimex.stock.repository.StockReceptionRepository;
import ma.co.orimex.stock.repository.search.StockReceptionSearchRepository;
import ma.co.orimex.stock.service.dto.StockReceptionDTO;
import ma.co.orimex.stock.service.mapper.StockReceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StockReception.
 */
@Service
@Transactional
public class StockReceptionServiceImpl implements StockReceptionService {

    private final Logger log = LoggerFactory.getLogger(StockReceptionServiceImpl.class);

    private final StockReceptionRepository stockReceptionRepository;

    private final StockReceptionMapper stockReceptionMapper;

    private final StockReceptionSearchRepository stockReceptionSearchRepository;

    public StockReceptionServiceImpl(StockReceptionRepository stockReceptionRepository, StockReceptionMapper stockReceptionMapper, StockReceptionSearchRepository stockReceptionSearchRepository) {
        this.stockReceptionRepository = stockReceptionRepository;
        this.stockReceptionMapper = stockReceptionMapper;
        this.stockReceptionSearchRepository = stockReceptionSearchRepository;
    }

    /**
     * Save a stockReception.
     *
     * @param stockReceptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockReceptionDTO save(StockReceptionDTO stockReceptionDTO) {
        log.debug("Request to save StockReception : {}", stockReceptionDTO);

        StockReception stockReception = stockReceptionMapper.toEntity(stockReceptionDTO);
        stockReception = stockReceptionRepository.save(stockReception);
        StockReceptionDTO result = stockReceptionMapper.toDto(stockReception);
        stockReceptionSearchRepository.save(stockReception);
        return result;
    }

    /**
     * Get all the stockReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockReceptions");
        return stockReceptionRepository.findAll(pageable)
            .map(stockReceptionMapper::toDto);
    }


    /**
     * Get one stockReception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockReceptionDTO> findOne(Long id) {
        log.debug("Request to get StockReception : {}", id);
        return stockReceptionRepository.findById(id)
            .map(stockReceptionMapper::toDto);
    }

    /**
     * Delete the stockReception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockReception : {}", id);
        stockReceptionRepository.deleteById(id);
        stockReceptionSearchRepository.deleteById(id);
    }

    /**
     * Search for the stockReception corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockReceptionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of StockReceptions for query {}", query);
        return stockReceptionSearchRepository.search(queryStringQuery(query), pageable)
            .map(stockReceptionMapper::toDto);
    }
}
