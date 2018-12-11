package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.BonService;
import ma.co.orimex.stock.domain.Bon;
import ma.co.orimex.stock.repository.BonRepository;
import ma.co.orimex.stock.repository.search.BonSearchRepository;
import ma.co.orimex.stock.service.dto.BonDTO;
import ma.co.orimex.stock.service.mapper.BonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Bon.
 */
@Service
@Transactional
public class BonServiceImpl implements BonService {

    private final Logger log = LoggerFactory.getLogger(BonServiceImpl.class);

    private final BonRepository bonRepository;

    private final BonMapper bonMapper;

    private final BonSearchRepository bonSearchRepository;

    public BonServiceImpl(BonRepository bonRepository, BonMapper bonMapper, BonSearchRepository bonSearchRepository) {
        this.bonRepository = bonRepository;
        this.bonMapper = bonMapper;
        this.bonSearchRepository = bonSearchRepository;
    }

    /**
     * Save a bon.
     *
     * @param bonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BonDTO save(BonDTO bonDTO) {
        log.debug("Request to save Bon : {}", bonDTO);

        Bon bon = bonMapper.toEntity(bonDTO);
        bon = bonRepository.save(bon);
        BonDTO result = bonMapper.toDto(bon);
        bonSearchRepository.save(bon);
        return result;
    }

    /**
     * Get all the bons.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bons");
        return bonRepository.findAll(pageable)
            .map(bonMapper::toDto);
    }


    /**
     * Get one bon by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BonDTO> findOne(Long id) {
        log.debug("Request to get Bon : {}", id);
        return bonRepository.findById(id)
            .map(bonMapper::toDto);
    }

    /**
     * Delete the bon by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bon : {}", id);
        bonRepository.deleteById(id);
        bonSearchRepository.deleteById(id);
    }

    /**
     * Search for the bon corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BonDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Bons for query {}", query);
        return bonSearchRepository.search(queryStringQuery(query), pageable)
            .map(bonMapper::toDto);
    }
}
