package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.StockConteneurReceptionService;
import ma.co.orimex.stock.domain.StockConteneurReception;
import ma.co.orimex.stock.repository.StockConteneurReceptionRepository;
import ma.co.orimex.stock.repository.search.StockConteneurReceptionSearchRepository;
import ma.co.orimex.stock.service.dto.StockConteneurReceptionDTO;
import ma.co.orimex.stock.service.mapper.StockConteneurReceptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StockConteneurReception.
 */
@Service
@Transactional
public class StockConteneurReceptionServiceImpl implements StockConteneurReceptionService {

    private final Logger log = LoggerFactory.getLogger(StockConteneurReceptionServiceImpl.class);

    private final StockConteneurReceptionRepository stockConteneurReceptionRepository;

    private final StockConteneurReceptionMapper stockConteneurReceptionMapper;

    private final StockConteneurReceptionSearchRepository stockConteneurReceptionSearchRepository;

    public StockConteneurReceptionServiceImpl(StockConteneurReceptionRepository stockConteneurReceptionRepository, StockConteneurReceptionMapper stockConteneurReceptionMapper, StockConteneurReceptionSearchRepository stockConteneurReceptionSearchRepository) {
        this.stockConteneurReceptionRepository = stockConteneurReceptionRepository;
        this.stockConteneurReceptionMapper = stockConteneurReceptionMapper;
        this.stockConteneurReceptionSearchRepository = stockConteneurReceptionSearchRepository;
    }

    /**
     * Save a stockConteneurReception.
     *
     * @param stockConteneurReceptionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StockConteneurReceptionDTO save(StockConteneurReceptionDTO stockConteneurReceptionDTO) {
        log.debug("Request to save StockConteneurReception : {}", stockConteneurReceptionDTO);

        StockConteneurReception stockConteneurReception = stockConteneurReceptionMapper.toEntity(stockConteneurReceptionDTO);
        stockConteneurReception = stockConteneurReceptionRepository.save(stockConteneurReception);
        StockConteneurReceptionDTO result = stockConteneurReceptionMapper.toDto(stockConteneurReception);
        stockConteneurReceptionSearchRepository.save(stockConteneurReception);
        return result;
    }

    /**
     * Get all the stockConteneurReceptions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockConteneurReceptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all StockConteneurReceptions");
        return stockConteneurReceptionRepository.findAll(pageable)
            .map(stockConteneurReceptionMapper::toDto);
    }


    /**
     * Get one stockConteneurReception by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StockConteneurReceptionDTO> findOne(Long id) {
        log.debug("Request to get StockConteneurReception : {}", id);
        return stockConteneurReceptionRepository.findById(id)
            .map(stockConteneurReceptionMapper::toDto);
    }

    /**
     * Delete the stockConteneurReception by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StockConteneurReception : {}", id);
        stockConteneurReceptionRepository.deleteById(id);
        stockConteneurReceptionSearchRepository.deleteById(id);
    }

    /**
     * Search for the stockConteneurReception corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<StockConteneurReceptionDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of StockConteneurReceptions for query {}", query);
        return stockConteneurReceptionSearchRepository.search(queryStringQuery(query), pageable)
            .map(stockConteneurReceptionMapper::toDto);
    }
}
