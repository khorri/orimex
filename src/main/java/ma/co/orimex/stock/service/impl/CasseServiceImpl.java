package ma.co.orimex.stock.service.impl;

import ma.co.orimex.stock.service.CasseService;
import ma.co.orimex.stock.domain.Casse;
import ma.co.orimex.stock.repository.CasseRepository;
import ma.co.orimex.stock.repository.search.CasseSearchRepository;
import ma.co.orimex.stock.service.dto.CasseDTO;
import ma.co.orimex.stock.service.mapper.CasseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Casse.
 */
@Service
@Transactional
public class CasseServiceImpl implements CasseService {

    private final Logger log = LoggerFactory.getLogger(CasseServiceImpl.class);

    private final CasseRepository casseRepository;

    private final CasseMapper casseMapper;

    private final CasseSearchRepository casseSearchRepository;

    public CasseServiceImpl(CasseRepository casseRepository, CasseMapper casseMapper, CasseSearchRepository casseSearchRepository) {
        this.casseRepository = casseRepository;
        this.casseMapper = casseMapper;
        this.casseSearchRepository = casseSearchRepository;
    }

    /**
     * Save a casse.
     *
     * @param casseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CasseDTO save(CasseDTO casseDTO) {
        log.debug("Request to save Casse : {}", casseDTO);

        Casse casse = casseMapper.toEntity(casseDTO);
        casse = casseRepository.save(casse);
        CasseDTO result = casseMapper.toDto(casse);
        casseSearchRepository.save(casse);
        return result;
    }

    /**
     * Get all the casses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CasseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Casses");
        return casseRepository.findAll(pageable)
            .map(casseMapper::toDto);
    }


    /**
     * Get one casse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CasseDTO> findOne(Long id) {
        log.debug("Request to get Casse : {}", id);
        return casseRepository.findById(id)
            .map(casseMapper::toDto);
    }

    /**
     * Delete the casse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Casse : {}", id);
        casseRepository.deleteById(id);
        casseSearchRepository.deleteById(id);
    }

    /**
     * Search for the casse corresponding to the query.
     *
     * @param query the query of the search
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CasseDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Casses for query {}", query);
        return casseSearchRepository.search(queryStringQuery(query), pageable)
            .map(casseMapper::toDto);
    }
}
