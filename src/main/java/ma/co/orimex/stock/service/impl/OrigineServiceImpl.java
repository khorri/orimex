package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.OrigineService;
import ma.co.orimex.stock.domain.Origine;
import ma.co.orimex.stock.repository.OrigineRepository;
import ma.co.orimex.stock.repository.search.OrigineSearchRepository;
import ma.co.orimex.stock.service.dto.OrigineDTO;
import ma.co.orimex.stock.service.mapper.OrigineMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Origine.
 */
@Service
@Transactional
public class OrigineServiceImpl implements OrigineService {

    private final Logger log = LoggerFactory.getLogger(OrigineServiceImpl.class);

    private final OrigineRepository origineRepository;

    private final OrigineMapper origineMapper;

    private final OrigineSearchRepository origineSearchRepository;

    public OrigineServiceImpl(OrigineRepository origineRepository, OrigineMapper origineMapper, OrigineSearchRepository origineSearchRepository) {
        this.origineRepository = origineRepository;
        this.origineMapper = origineMapper;
        this.origineSearchRepository = origineSearchRepository;
    }

    /**
     * Save a origine.
     *
     * @param origineDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrigineDTO save(OrigineDTO origineDTO) {
        log.debug("Request to save Origine : {}", origineDTO);

        Origine origine = origineMapper.toEntity(origineDTO);
        origine = origineRepository.save(origine);
        OrigineDTO result = origineMapper.toDto(origine);
        origineSearchRepository.save(origine);
        return result;
    }

    /**
     * Get all the origines.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrigineDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Origines");
        return origineRepository.findAll(pageable)
            .map(origineMapper::toDto);
    }


    /**
     * Get one origine by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrigineDTO> findOne(Long id) {
        log.debug("Request to get Origine : {}", id);
        return origineRepository.findById(id)
            .map(origineMapper::toDto);
    }

    /**
     * Delete the origine by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Origine : {}", id);
        origineRepository.deleteById(id);
        origineSearchRepository.deleteById(id);
    }

    /**
     * Search for the origine corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OrigineDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Origines for query {}", query);
        return origineSearchRepository.search(queryStringQuery(query), pageable)
            .map(origineMapper::toDto);
    }
}
